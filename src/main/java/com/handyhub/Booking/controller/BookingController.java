package com.handyhub.Booking.controller;


import com.handyhub.Booking.dto.BookingDTO;
import com.handyhub.Booking.modal.BookingStatus;
import com.handyhub.Booking.service.BookingService;
import com.handyhub.shared.exception.custom.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/bookings")
public class BookingController {

    @Autowired private BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingDTO> create(@RequestBody BookingDTO dto) {
        return ResponseEntity.ok(bookingService.createBooking(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.getById(id));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<BookingDTO> updateStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(bookingService.updateBookingStatus(id, BookingStatus.valueOf(status.toUpperCase())));
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<BookingDTO> cancel(@PathVariable Long id, @RequestBody Map<String, String> body) {
        return ResponseEntity.ok(bookingService.cancelBooking(id, body.getOrDefault("reason", "Cancelled")));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BookingDTO> patch(@PathVariable Long id, @RequestBody BookingDTO dto) {
        return ResponseEntity.ok(bookingService.patchBooking(id, dto));
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<BookingDTO>> byCustomer(@PathVariable("id") Long id) {
        return ResponseEntity.ok(bookingService.getBookingsByCustomer(id));
    }

    @GetMapping("/worker/{id}")
    public ResponseEntity<List<BookingDTO>> byWorker(@PathVariable("id") Long id) {
        return ResponseEntity.ok(bookingService.getBookingsByWorker(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookingDTO>> search(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String priority,
            @RequestParam(required = false) Long workerId,
            @RequestParam(required = false) Long customerId,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long subcategoryId,
            @RequestParam(required = false) String from,
            @RequestParam(required = false) String to
    ) {
        return ResponseEntity.ok(bookingService.search(status, priority, workerId, customerId, categoryId, subcategoryId, from, to));
    }
}
