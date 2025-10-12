package com.handyhub.Booking.service.imp;

import com.handyhub.Booking.dto.BookingDTO;
import com.handyhub.Booking.mapper.BookingMapper;
import com.handyhub.Booking.modal.*;
import com.handyhub.Booking.repo.BookingRepository;
import com.handyhub.Booking.service.BookingService;
import com.handyhub.Itemservice.modal.ServiceCategory;
import com.handyhub.Itemservice.modal.ServiceSubcategory;
import com.handyhub.Itemservice.repo.ServiceCategoryRepository;
import com.handyhub.Itemservice.repo.ServiceSubcategoryRepository;
import com.handyhub.shared.exception.custom.EntityNotFound;
import com.handyhub.user.model.User;
import com.handyhub.user.model.WorkerProfile;
import com.handyhub.user.repository.UserRepository;
import com.handyhub.user.repository.WorkerProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkerProfileRepository workerProfileRepository;

    @Autowired
    private ServiceSubcategoryRepository subcategoryRepository;

    @Autowired
    private ServiceCategoryRepository serviceCategoryRepository;

    @Override
    public BookingDTO createBooking(BookingDTO dto) {
        Booking booking = BookingMapper.toEntity(dto);

        User customer = userRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new EntityNotFound("Customer not found"));
        WorkerProfile worker = workerProfileRepository.findById(dto.getWorkerId())
                .orElseThrow(() -> new EntityNotFound("Worker not found"));

        booking.setCustomer(customer);
        booking.setWorker(worker);

        // Optional references
        booking.setServiceSubcategory(
                dto.getSubcategoryId() != null
                        ? subcategoryRepository.findById(dto.getSubcategoryId()).orElse(null)
                        : null
        );


        booking.setServiceCategory(dto.getCategoryId() != null
                ? serviceCategoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new EntityNotFound("Category not found"))
                : null);

        // Safe defaults
        if (booking.getPriority() == null) booking.setPriority(BookingPriority.SCHEDULED);
        if (booking.getPaymentMethod() == null) booking.setPaymentMethod(PaymentMethod.CASH);
        if (booking.getMaterialsProvidedBy() == null) booking.setMaterialsProvidedBy(MaterialProvider.CUSTOMER);
        if (booking.getConsentToSharePhone() == null) booking.setConsentToSharePhone(true);
        if (booking.getServiceLocationType() == null) booking.setServiceLocationType(ServiceLocationType.HOME);
        if (booking.getTermsAcceptedAt() == null) booking.setTermsAcceptedAt(LocalDateTime.now());
        if (booking.getLanguagePref() == null) booking.setLanguagePref("EN");

        booking.setStatus(BookingStatus.PENDING);
        Booking saved = bookingRepository.save(booking);

        return BookingMapper.toDTO(saved);
    }


    @Override
    public BookingDTO updateBookingStatus(Long bookingId, BookingStatus status) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new EntityNotFound("Booking not found"));

        booking.setStatus(status);
        Booking updated = bookingRepository.save(booking);
        return BookingMapper.toDTO(updated);
    }

    @Override
    public List<BookingDTO> getBookingsByCustomer(Long customerId) {
        List<Booking> bookings = bookingRepository.findByCustomerId(customerId);
        return bookings.stream().map(BookingMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public BookingDTO getById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFound("Booking not found"));
        return BookingMapper.toDTO(booking);
    }
    @Override
    public List<BookingDTO> getBookingsByWorker(Long workerId) {
        List<Booking> bookings = bookingRepository.findByWorkerId(workerId);
        return bookings.stream().map(BookingMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public BookingDTO cancelBooking(Long id, String reason) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFound("Booking not found"));
        booking.setStatus(BookingStatus.REJECTED);
        booking.setCancellationReason(reason);
        booking.setUpdatedAt(LocalDateTime.now());
        return BookingMapper.toDTO(bookingRepository.save(booking));
    }

    @Override
    public BookingDTO patchBooking(Long id, BookingDTO patch) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFound("Booking not found"));

        if (patch.getFinalPrice() != null) booking.setFinalPrice(patch.getFinalPrice());
        if (patch.getPaymentMethod() != null) booking.setPaymentMethod(patch.getPaymentMethod());
        if (patch.getNeedGSTInvoice() != null) booking.setNeedGSTInvoice(patch.getNeedGSTInvoice());
        if (patch.getAccessNotes() != null) booking.setAccessNotes(patch.getAccessNotes());
        if (patch.getAddress() != null) booking.setAddress(patch.getAddress());
        if (patch.getEstimatedPrice() != null) booking.setEstimatedPrice(patch.getEstimatedPrice());

        booking.setUpdatedAt(LocalDateTime.now());
        return BookingMapper.toDTO(bookingRepository.save(booking));
    }

    @Override
    public List<BookingDTO> search(String status, String priority, Long workerId, Long customerId,
                                   Long categoryId, Long subcategoryId, String from, String to) {

        Specification<Booking> spec = Specification.allOf(); // ✅ Replaces Specification.where(null)

        if (status != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("status"), BookingStatus.valueOf(status.toUpperCase())));
        }

        if (priority != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("priority"), BookingPriority.valueOf(priority.toUpperCase())));
        }

        if (workerId != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("worker").get("id"), workerId));
        }

        if (customerId != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("customer").get("id"), customerId));
        }

        if (categoryId != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("serviceCategory").get("categoryId"), categoryId));
        }

        if (subcategoryId != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("serviceSubcategory").get("subcategoryId"), subcategoryId));
        }

        try {
            if (from != null) {
                spec = spec.and((root, query, cb) ->
                        cb.greaterThanOrEqualTo(root.get("scheduledStartTime"), LocalDateTime.parse(from)));
            }
            if (to != null) {
                spec = spec.and((root, query, cb) ->
                        cb.lessThanOrEqualTo(root.get("scheduledEndTime"), LocalDateTime.parse(to)));
            }
        } catch (DateTimeParseException ignored) {
        }

        // ✅ New API for findAll(Specification)
        return bookingRepository.findAll(spec, org.springframework.data.domain.Sort.unsorted())
                .stream()
                .map(BookingMapper::toDTO)
                .collect(Collectors.toList());
    }

}
