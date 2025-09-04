package com.handyhub.Itemservice.dto;

public class ServiceSubcategoryDto {

    private Long subcategoryId;
    private Long categoryId; // parent category id
    private String subcategoryName;
    private String subcategoryDescription;
    private Integer averageDuration;

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
