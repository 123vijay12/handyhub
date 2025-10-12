package com.handyhub.Booking.modal;

import com.handyhub.Itemservice.modal.ServiceCategory;
import com.handyhub.Itemservice.modal.ServiceSubcategory;
import com.handyhub.user.model.User;
import com.handyhub.user.model.WorkerProfile;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Who booked
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

    // Which worker
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_id", nullable = false)
    private WorkerProfile worker;

    // Service info
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private ServiceCategory serviceCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subcategory_id")
    private ServiceSubcategory serviceSubcategory;

    // Time info
    private LocalDateTime bookingTime = LocalDateTime.now();
    private LocalDateTime scheduledStartTime;
    private LocalDateTime scheduledEndTime;

    // Pricing
    private Double estimatedPrice;
    private Double finalPrice;

    // Location
    private String address;
    private Double latitude;
    private Double longitude;

    // Description / job summary
    @Column(length = 500)
    private String description;

    // Booking status
    @Enumerated(EnumType.STRING)
    private BookingStatus status = BookingStatus.PENDING;

    private String cancellationReason;

    // Priority (URGENT / SCHEDULED / FLEXIBLE)
    @Enumerated(EnumType.STRING)
    private BookingPriority priority = BookingPriority.SCHEDULED;

    // Contact details
    private String contactPhone;
    private Boolean consentToSharePhone = true;
    private String alternateContactName;
    private String alternateContactPhone;

    // Materials, Payment, Coupon
    @Enumerated(EnumType.STRING)
    private MaterialProvider materialsProvidedBy = MaterialProvider.CUSTOMER;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod = PaymentMethod.CASH;

    private String couponCode;

    // Expected duration & notes
    private Integer expectedDurationMins;
    @Column(length = 500)
    private String accessNotes;

    // Language preference (EN, HI, etc.)
    private String languagePref = "EN";

    // Billing details
    private Boolean needGSTInvoice = false;
    private String gstNumber;
    private String companyName;

    // Location type (HOME / OFFICE / OTHER)
    @Enumerated(EnumType.STRING)
    private ServiceLocationType serviceLocationType = ServiceLocationType.HOME;

    // Terms acceptance
    private LocalDateTime termsAcceptedAt = LocalDateTime.now();

    // Relation to payment (if implemented)
    @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL)
    private Payment payment;

    // Audit
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    /* -------------------- Getters & Setters -------------------- */

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getCustomer() { return customer; }
    public void setCustomer(User customer) { this.customer = customer; }

    public WorkerProfile getWorker() { return worker; }
    public void setWorker(WorkerProfile worker) { this.worker = worker; }

    public ServiceCategory getServiceCategory() { return serviceCategory; }
    public void setServiceCategory(ServiceCategory serviceCategory) { this.serviceCategory = serviceCategory; }

    public ServiceSubcategory getServiceSubcategory() { return serviceSubcategory; }
    public void setServiceSubcategory(ServiceSubcategory serviceSubcategory) { this.serviceSubcategory = serviceSubcategory; }

    public LocalDateTime getBookingTime() { return bookingTime; }
    public void setBookingTime(LocalDateTime bookingTime) { this.bookingTime = bookingTime; }

    public LocalDateTime getScheduledStartTime() { return scheduledStartTime; }
    public void setScheduledStartTime(LocalDateTime scheduledStartTime) { this.scheduledStartTime = scheduledStartTime; }

    public LocalDateTime getScheduledEndTime() { return scheduledEndTime; }
    public void setScheduledEndTime(LocalDateTime scheduledEndTime) { this.scheduledEndTime = scheduledEndTime; }

    public Double getEstimatedPrice() { return estimatedPrice; }
    public void setEstimatedPrice(Double estimatedPrice) { this.estimatedPrice = estimatedPrice; }

    public Double getFinalPrice() { return finalPrice; }
    public void setFinalPrice(Double finalPrice) { this.finalPrice = finalPrice; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BookingStatus getStatus() { return status; }
    public void setStatus(BookingStatus status) { this.status = status; }

    public String getCancellationReason() { return cancellationReason; }
    public void setCancellationReason(String cancellationReason) { this.cancellationReason = cancellationReason; }

    public BookingPriority getPriority() { return priority; }
    public void setPriority(BookingPriority priority) { this.priority = priority; }

    public String getContactPhone() { return contactPhone; }
    public void setContactPhone(String contactPhone) { this.contactPhone = contactPhone; }

    public Boolean getConsentToSharePhone() { return consentToSharePhone; }
    public void setConsentToSharePhone(Boolean consentToSharePhone) { this.consentToSharePhone = consentToSharePhone; }

    public String getAlternateContactName() { return alternateContactName; }
    public void setAlternateContactName(String alternateContactName) { this.alternateContactName = alternateContactName; }

    public String getAlternateContactPhone() { return alternateContactPhone; }
    public void setAlternateContactPhone(String alternateContactPhone) { this.alternateContactPhone = alternateContactPhone; }

    public MaterialProvider getMaterialsProvidedBy() { return materialsProvidedBy; }
    public void setMaterialsProvidedBy(MaterialProvider materialsProvidedBy) { this.materialsProvidedBy = materialsProvidedBy; }

    public PaymentMethod getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(PaymentMethod paymentMethod) { this.paymentMethod = paymentMethod; }

    public String getCouponCode() { return couponCode; }
    public void setCouponCode(String couponCode) { this.couponCode = couponCode; }

    public Integer getExpectedDurationMins() { return expectedDurationMins; }
    public void setExpectedDurationMins(Integer expectedDurationMins) { this.expectedDurationMins = expectedDurationMins; }

    public String getAccessNotes() { return accessNotes; }
    public void setAccessNotes(String accessNotes) { this.accessNotes = accessNotes; }

    public String getLanguagePref() { return languagePref; }
    public void setLanguagePref(String languagePref) { this.languagePref = languagePref; }

    public Boolean getNeedGSTInvoice() { return needGSTInvoice; }
    public void setNeedGSTInvoice(Boolean needGSTInvoice) { this.needGSTInvoice = needGSTInvoice; }

    public String getGstNumber() { return gstNumber; }
    public void setGstNumber(String gstNumber) { this.gstNumber = gstNumber; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public ServiceLocationType getServiceLocationType() { return serviceLocationType; }
    public void setServiceLocationType(ServiceLocationType serviceLocationType) { this.serviceLocationType = serviceLocationType; }

    public LocalDateTime getTermsAcceptedAt() { return termsAcceptedAt; }
    public void setTermsAcceptedAt(LocalDateTime termsAcceptedAt) { this.termsAcceptedAt = termsAcceptedAt; }

    public Payment getPayment() { return payment; }
    public void setPayment(Payment payment) { this.payment = payment; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
