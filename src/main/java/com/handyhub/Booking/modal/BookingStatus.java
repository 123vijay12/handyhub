package com.handyhub.Booking.modal;

public enum BookingStatus {
    PENDING,        // created by customer, waiting for worker response
    ACCEPTED,       // worker accepted
    REJECTED,       // worker rejected
    IN_PROGRESS,    // service started
    COMPLETED,      // done
    CANCELLED
}
