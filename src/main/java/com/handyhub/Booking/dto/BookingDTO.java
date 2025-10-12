package com.handyhub.Booking.dto;

import com.handyhub.Booking.modal.*;

import java.time.LocalDateTime;

public class BookingDTO {

    private Long id;
    private Long customerId;
    private Long workerId;
    private Long subcategoryId;
    private Long categoryId;

    private LocalDateTime scheduledStartTime;
    private LocalDateTime scheduledEndTime;
    private String address;
    private String description;
    private Double estimatedPrice;
    private Double finalPrice;
    private BookingStatus status;
    private String cancellationReason;

    // New fields (optional)
    private String contactPhone;
    private Double latitude;
    private Double longitude;
    private BookingPriority priority;
    private PaymentMethod paymentMethod;
    private String couponCode;
    private MaterialProvider materialsProvidedBy;
    private Integer expectedDurationMins;
    private String accessNotes;
    private String languagePref;
    private Boolean needGSTInvoice;
    private String gstNumber;
    private String companyName;
    private Boolean consentToSharePhone;
    private String alternateContactName;
    private String alternateContactPhone;
    private ServiceLocationType serviceLocationType;
    private LocalDateTime termsAcceptedAt;

    // Getters and setters ...
    // (generate normally or use Lombok @Data)


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    public Long getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(Long subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public LocalDateTime getScheduledStartTime() {
        return scheduledStartTime;
    }

    public void setScheduledStartTime(LocalDateTime scheduledStartTime) {
        this.scheduledStartTime = scheduledStartTime;
    }

    public LocalDateTime getScheduledEndTime() {
        return scheduledEndTime;
    }

    public void setScheduledEndTime(LocalDateTime scheduledEndTime) {
        this.scheduledEndTime = scheduledEndTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getEstimatedPrice() {
        return estimatedPrice;
    }

    public void setEstimatedPrice(Double estimatedPrice) {
        this.estimatedPrice = estimatedPrice;
    }

    public Double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public String getCancellationReason() {
        return cancellationReason;
    }

    public void setCancellationReason(String cancellationReason) {
        this.cancellationReason = cancellationReason;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public BookingPriority getPriority() {
        return priority;
    }

    public void setPriority(BookingPriority priority) {
        this.priority = priority;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public MaterialProvider getMaterialsProvidedBy() {
        return materialsProvidedBy;
    }

    public void setMaterialsProvidedBy(MaterialProvider materialsProvidedBy) {
        this.materialsProvidedBy = materialsProvidedBy;
    }

    public Integer getExpectedDurationMins() {
        return expectedDurationMins;
    }

    public void setExpectedDurationMins(Integer expectedDurationMins) {
        this.expectedDurationMins = expectedDurationMins;
    }

    public String getAccessNotes() {
        return accessNotes;
    }

    public void setAccessNotes(String accessNotes) {
        this.accessNotes = accessNotes;
    }

    public String getLanguagePref() {
        return languagePref;
    }

    public void setLanguagePref(String languagePref) {
        this.languagePref = languagePref;
    }

    public Boolean getNeedGSTInvoice() {
        return needGSTInvoice;
    }

    public void setNeedGSTInvoice(Boolean needGSTInvoice) {
        this.needGSTInvoice = needGSTInvoice;
    }

    public String getGstNumber() {
        return gstNumber;
    }

    public void setGstNumber(String gstNumber) {
        this.gstNumber = gstNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Boolean getConsentToSharePhone() {
        return consentToSharePhone;
    }

    public void setConsentToSharePhone(Boolean consentToSharePhone) {
        this.consentToSharePhone = consentToSharePhone;
    }

    public String getAlternateContactName() {
        return alternateContactName;
    }

    public void setAlternateContactName(String alternateContactName) {
        this.alternateContactName = alternateContactName;
    }

    public String getAlternateContactPhone() {
        return alternateContactPhone;
    }

    public void setAlternateContactPhone(String alternateContactPhone) {
        this.alternateContactPhone = alternateContactPhone;
    }

    public ServiceLocationType getServiceLocationType() {
        return serviceLocationType;
    }

    public void setServiceLocationType(ServiceLocationType serviceLocationType) {
        this.serviceLocationType = serviceLocationType;
    }

    public LocalDateTime getTermsAcceptedAt() {
        return termsAcceptedAt;
    }

    public void setTermsAcceptedAt(LocalDateTime termsAcceptedAt) {
        this.termsAcceptedAt = termsAcceptedAt;
    }
}
