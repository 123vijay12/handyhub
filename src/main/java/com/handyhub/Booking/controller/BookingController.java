package com.handyhub.Booking.controller;


import com.handyhub.Booking.dto.BookingDTO;
import com.handyhub.Booking.modal.BookingStatus;
import com.handyhub.Booking.service.BookingService;
import com.handyhub.shared.exception.custom.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // Create a new booking
    @PostMapping
    public ResponseEntity<BookingDTO> createBooking(@RequestBody BookingDTO bookingDTO) {
        BookingDTO createdBooking = bookingService.createBooking(bookingDTO);
        return ResponseEntity.ok(createdBooking);
    }

    // Update booking status
    @PatchMapping("/{id}/status")
    public ResponseEntity<BookingDTO> updateBookingStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        BookingStatus bookingStatus;
        try {
            bookingStatus = BookingStatus.valueOf(status.toUpperCase());
        } catch (ValidationException e) {
            throw new RuntimeException("Invalid booking status: " + status);
        }

        BookingDTO updatedBooking = bookingService.updateBookingStatus(id, bookingStatus);
        return ResponseEntity.ok(updatedBooking);
    }


    // Get all bookings for a customer
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<BookingDTO>> getBookingsByCustomer(@PathVariable Long customerId) {
        List<BookingDTO> bookings = bookingService.getBookingsByCustomer(customerId);
        return ResponseEntity.ok(bookings);
    }

    // Get all bookings for a worker
    @GetMapping("/worker/{workerId}")
    public ResponseEntity<List<BookingDTO>> getBookingsByWorker(@PathVariable Long workerId) {
        List<BookingDTO> bookings = bookingService.getBookingsByWorker(workerId);
        return ResponseEntity.ok(bookings);
    }
}
