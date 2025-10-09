package com.handyhub.Booking.service.imp;

import com.handyhub.Booking.dto.BookingDTO;
import com.handyhub.Booking.mapper.BookingMapper;
import com.handyhub.Booking.modal.Booking;
import com.handyhub.Booking.modal.BookingStatus;
import com.handyhub.Booking.repo.BookingRepository;
import com.handyhub.Booking.service.BookingService;
import com.handyhub.Itemservice.modal.ServiceSubcategory;
import com.handyhub.Itemservice.repo.ServiceSubcategoryRepository;
import com.handyhub.shared.exception.custom.EntityNotFound;
import com.handyhub.user.model.User;
import com.handyhub.user.model.WorkerProfile;
import com.handyhub.user.repository.UserRepository;
import com.handyhub.user.repository.WorkerProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public BookingDTO createBooking(BookingDTO bookingDTO) {
        Booking booking = BookingMapper.toEntity(bookingDTO);

        // fetch and set nested objects
        User customer = userRepository.findById(bookingDTO.getCustomerId())
                .orElseThrow(() -> new EntityNotFound("Customer not found"));
        WorkerProfile worker = workerProfileRepository.findById(bookingDTO.getWorkerId())
                .orElseThrow(() -> new EntityNotFound("Worker not found"));
        ServiceSubcategory subcategory = subcategoryRepository.findById(bookingDTO.getSubcategoryId())
                .orElseThrow(() -> new EntityNotFound("Subcategory not found"));

        booking.setCustomer(customer);
        booking.setWorker(worker);
        booking.setServiceSubcategory(subcategory);
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
    public List<BookingDTO> getBookingsByWorker(Long workerId) {
        List<Booking> bookings = bookingRepository.findByWorkerId(workerId);
        return bookings.stream().map(BookingMapper::toDTO).collect(Collectors.toList());
    }
}
