package com.handyhub.Itemservice.modal;

import jakarta.persistence.*;

@Entity
@Table(name = "service_subcategories",
        uniqueConstraints = @UniqueConstraint(columnNames = {"category_id", "subcategory_name"}))
public class ServiceSubcategory {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subcategory_id")
    private Long subcategoryId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_subcategory_category"))
    private ServiceCategory category;

    @Column(name = "subcategory_name", nullable = false, length = 100)
    private String subcategoryName;

    @Column(name = "subcategory_description", columnDefinition = "TEXT")
    private String subcategoryDescription;

    /**
     * averageDuration in minutes - optional business metadata (kept in entity since it's business data).
     */
    @Column(name = "average_duration")
    private Integer averageDuration;

    public Long getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(Long subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    public ServiceCategory getCategory() {
        return category;
    }

    public void setCategory(ServiceCategory category) {
        this.category = category;
    }

    public String getSubcategoryName() {
        return subcategoryName;
    }

    public void setSubcategoryName(String subcategoryName) {
        this.subcategoryName = subcategoryName;
    }

    public String getSubcategoryDescription() {
        return subcategoryDescription;
    }

    public void setSubcategoryDescription(String subcategoryDescription) {
        this.subcategoryDescription = subcategoryDescription;
    }

    public Integer getAverageDuration() {
        return averageDuration;
    }

    public void setAverageDuration(Integer averageDuration) {
        this.averageDuration = averageDuration;
    }
}
