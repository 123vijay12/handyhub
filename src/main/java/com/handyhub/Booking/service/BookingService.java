package com.handyhub.Booking.service;

import com.handyhub.Booking.dto.BookingDTO;
import com.handyhub.Booking.modal.BookingStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookingService {

    BookingDTO createBooking(BookingDTO bookingDTO);

    BookingDTO updateBookingStatus(Long bookingId, BookingStatus status);

    List<BookingDTO> getBookingsByCustomer(Long customerId);

    List<BookingDTO> getBookingsByWorker(Long workerId);
}
