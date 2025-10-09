package com.handyhub.user.model;

import com.handyhub.Itemservice.mapper.SubcategoryMapper;
import com.handyhub.Itemservice.modal.ServiceCategory;
import com.handyhub.Itemservice.modal.ServiceSubcategory;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class WorkerProfile{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;
    private String experience;   // e.g. "5 years"
    private Double hourlyRate;   // pricing
    private String serviceArea;  // location/coverage

    private boolean available = true;

    // ratings received
    @OneToMany(mappedBy = "ratedTo", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Rating> ratingsReceived = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "worker_skill",
            joinColumns = @JoinColumn(name = "worker_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private Set<Skill> skills;

    @ManyToOne
    @JoinColumn(name = "sub_category_id")
     private ServiceSubcategory serviceSubcategory;
    // getters & setters

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ServiceCategory serviceCategory;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public Double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(Double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public String getServiceArea() {
        return serviceArea;
    }

    public void setServiceArea(String serviceArea) {
        this.serviceArea = serviceArea;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Set<Rating> getRatingsReceived() {
        return ratingsReceived;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public ServiceSubcategory getServiceSubcategory() {
        return serviceSubcategory;
    }

    public void setServiceSubcategory(ServiceSubcategory serviceSubcategory) {
        this.serviceSubcategory = serviceSubcategory;
    }

    public ServiceCategory getServiceCategory() {
        return serviceCategory;
    }

    public void setServiceCategory(ServiceCategory serviceCategory) {
        this.serviceCategory = serviceCategory;
    }

    public void setRatingsReceived(Set<Rating> ratingsReceived) {
        this.ratingsReceived = ratingsReceived;
    }
}
