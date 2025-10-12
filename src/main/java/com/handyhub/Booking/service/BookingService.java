package com.handyhub.Booking.service;

import com.handyhub.Booking.dto.BookingDTO;
import com.handyhub.Booking.modal.BookingStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookingService {

    BookingDTO createBooking(BookingDTO bookingDTO);

    BookingDTO getById(Long id);
    BookingDTO updateBookingStatus(Long bookingId, BookingStatus status);

    List<BookingDTO> getBookingsByCustomer(Long customerId);

    List<BookingDTO> getBookingsByWorker(Long workerId);
    BookingDTO cancelBooking(Long bookingId, String reason);

    BookingDTO patchBooking(Long bookingId, BookingDTO patchDTO);

    List<BookingDTO> search(String status, String priority, Long workerId, Long customerId,
                            Long categoryId, Long subcategoryId, String from, String to);
}
