package com.handyhub.Booking.repo;

import com.handyhub.Booking.modal.Booking;
import com.handyhub.Booking.modal.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> , JpaSpecificationExecutor<Booking> {
    List<Booking> findByCustomerId(Long customerId);
    List<Booking> findByWorkerId(Long workerId);
    List<Booking> findByStatus(BookingStatus status);
}
