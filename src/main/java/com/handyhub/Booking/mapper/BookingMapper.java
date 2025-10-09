package com.handyhub.Booking.mapper;


import com.handyhub.Booking.dto.BookingDTO;
import com.handyhub.Booking.modal.Booking;
import com.handyhub.user.model.User;
import com.handyhub.user.model.WorkerProfile;
import com.handyhub.Itemservice.modal.ServiceSubcategory;
import org.springframework.stereotype.Component;


public class BookingMapper {

    // Convert DTO -> Entity
    public static Booking toEntity(BookingDTO dto) {
        if (dto == null) return null;

        Booking booking = new Booking();

        booking.setId(dto.getId());

        // Nested objects: just set IDs for now (actual entities should be fetched in service)
        if (dto.getCustomerId() != null) {
            User customer = new User();
            customer.setId(dto.getCustomerId());
            booking.setCustomer(customer);
        }

        if (dto.getWorkerId() != null) {
            WorkerProfile worker = new WorkerProfile();
            worker.setId(dto.getWorkerId());
            booking.setWorker(worker);
        }

        if (dto.getSubcategoryId() != null) {
            ServiceSubcategory subcategory = new ServiceSubcategory();
            subcategory.setSubcategoryId(dto.getSubcategoryId());
            booking.setServiceSubcategory(subcategory);
        }

        booking.setScheduledStartTime(dto.getScheduledStartTime());
        booking.setScheduledEndTime(dto.getScheduledEndTime());
        booking.setAddress(dto.getAddress());
        booking.setDescription(dto.getDescription());
        booking.setEstimatedPrice(dto.getEstimatedPrice());
        booking.setFinalPrice(dto.getFinalPrice());
        booking.setStatus(dto.getStatus());
        booking.setCancellationReason(dto.getCancellationReason());

        return booking;
    }

    // Convert Entity -> DTO
    public static BookingDTO toDTO(Booking booking) {
        if (booking == null) return null;

        BookingDTO dto = new BookingDTO();
        dto.setId(booking.getId());

        if (booking.getCustomer() != null)
            dto.setCustomerId(booking.getCustomer().getId());

        if (booking.getWorker() != null)
            dto.setWorkerId(booking.getWorker().getId());

        if (booking.getServiceSubcategory() != null)
            dto.setSubcategoryId(booking.getServiceSubcategory().getSubcategoryId());

        dto.setScheduledStartTime(booking.getScheduledStartTime());
        dto.setScheduledEndTime(booking.getScheduledEndTime());
        dto.setAddress(booking.getAddress());
        dto.setDescription(booking.getDescription());
        dto.setEstimatedPrice(booking.getEstimatedPrice());
        dto.setFinalPrice(booking.getFinalPrice());
        dto.setStatus(booking.getStatus());
        dto.setCancellationReason(booking.getCancellationReason());

        return dto;
    }
}
