package com.handyhub.Itemservice.modal;

import com.handyhub.shared.util.Auditable;
import jakarta.persistence.*;

@Table(name = "service_categories",
        uniqueConstraints = @UniqueConstraint(columnNames = {"category_name"}))
@Entity
public class ServiceCategory extends Auditable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "category_id")
        private Long categoryId;

        @Column(name = "category_name", nullable = false, length = 100)
        private String categoryName;

        @Column(name = "category_description", columnDefinition = "TEXT")
        private String categoryDescription;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }
}
