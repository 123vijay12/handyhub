package com.handyhub.Booking.mapper;

import com.handyhub.Booking.dto.BookingDTO;
import com.handyhub.Booking.modal.Booking;
import com.handyhub.Itemservice.modal.ServiceSubcategory;
import com.handyhub.Itemservice.modal.ServiceCategory;
import com.handyhub.user.model.User;
import com.handyhub.user.model.WorkerProfile;

public class BookingMapper {

    public static Booking toEntity(BookingDTO dto) {
        if (dto == null) return null;

        Booking booking = new Booking();
        booking.setId(dto.getId());

        if (dto.getCustomerId() != null) {
            User u = new User();
            u.setId(dto.getCustomerId());
            booking.setCustomer(u);
        }
        if (dto.getWorkerId() != null) {
            WorkerProfile w = new WorkerProfile();
            w.setId(dto.getWorkerId());
            booking.setWorker(w);
        }
        if (dto.getSubcategoryId() != null) {
            ServiceSubcategory sub = new ServiceSubcategory();
            sub.setSubcategoryId(dto.getSubcategoryId());
            booking.setServiceSubcategory(sub);
        }
        if (dto.getCategoryId()!= null) {
            ServiceCategory cat = new ServiceCategory();
            cat.setCategoryId(dto.getCategoryId());
            booking.setServiceCategory(cat);
        }

        booking.setScheduledStartTime(dto.getScheduledStartTime());
        booking.setScheduledEndTime(dto.getScheduledEndTime());
        booking.setAddress(dto.getAddress());
        booking.setDescription(dto.getDescription());
        booking.setEstimatedPrice(dto.getEstimatedPrice());
        booking.setFinalPrice(dto.getFinalPrice());
        booking.setStatus(dto.getStatus());
        booking.setCancellationReason(dto.getCancellationReason());

        // New fields
        booking.setContactPhone(dto.getContactPhone());
        booking.setLatitude(dto.getLatitude());
        booking.setLongitude(dto.getLongitude());
        booking.setPriority(dto.getPriority());
        booking.setPaymentMethod(dto.getPaymentMethod());
        booking.setCouponCode(dto.getCouponCode());
        booking.setMaterialsProvidedBy(dto.getMaterialsProvidedBy());
        booking.setExpectedDurationMins(dto.getExpectedDurationMins());
        booking.setAccessNotes(dto.getAccessNotes());
        booking.setLanguagePref(dto.getLanguagePref());
        booking.setNeedGSTInvoice(dto.getNeedGSTInvoice());
        booking.setGstNumber(dto.getGstNumber());
        booking.setCompanyName(dto.getCompanyName());
        booking.setConsentToSharePhone(dto.getConsentToSharePhone());
        booking.setAlternateContactName(dto.getAlternateContactName());
        booking.setAlternateContactPhone(dto.getAlternateContactPhone());
        booking.setServiceLocationType(dto.getServiceLocationType());
        booking.setTermsAcceptedAt(dto.getTermsAcceptedAt());

        return booking;
    }

    public static BookingDTO toDTO(Booking booking) {
        if (booking == null) return null;

        BookingDTO dto = new BookingDTO();
        dto.setId(booking.getId());

        if (booking.getCustomer() != null) dto.setCustomerId(booking.getCustomer().getId());
        if (booking.getWorker() != null) dto.setWorkerId(booking.getWorker().getId());
        if (booking.getServiceSubcategory() != null)
            dto.setSubcategoryId(booking.getServiceSubcategory().getSubcategoryId());
        if (booking.getServiceCategory() != null)
            dto.setCategoryId(booking.getServiceCategory().getCategoryId());

        dto.setScheduledStartTime(booking.getScheduledStartTime());
        dto.setScheduledEndTime(booking.getScheduledEndTime());
        dto.setAddress(booking.getAddress());
        dto.setDescription(booking.getDescription());
        dto.setEstimatedPrice(booking.getEstimatedPrice());
        dto.setFinalPrice(booking.getFinalPrice());
        dto.setStatus(booking.getStatus());
        dto.setCancellationReason(booking.getCancellationReason());

        // New fields
        dto.setContactPhone(booking.getContactPhone());
        dto.setLatitude(booking.getLatitude());
        dto.setLongitude(booking.getLongitude());
        dto.setPriority(booking.getPriority());
        dto.setPaymentMethod(booking.getPaymentMethod());
        dto.setCouponCode(booking.getCouponCode());
        dto.setMaterialsProvidedBy(booking.getMaterialsProvidedBy());
        dto.setExpectedDurationMins(booking.getExpectedDurationMins());
        dto.setAccessNotes(booking.getAccessNotes());
        dto.setLanguagePref(booking.getLanguagePref());
        dto.setNeedGSTInvoice(booking.getNeedGSTInvoice());
        dto.setGstNumber(booking.getGstNumber());
        dto.setCompanyName(booking.getCompanyName());
        dto.setConsentToSharePhone(booking.getConsentToSharePhone());
        dto.setAlternateContactName(booking.getAlternateContactName());
        dto.setAlternateContactPhone(booking.getAlternateContactPhone());
        dto.setServiceLocationType(booking.getServiceLocationType());
        dto.setTermsAcceptedAt(booking.getTermsAcceptedAt());

        return dto;
    }
}
