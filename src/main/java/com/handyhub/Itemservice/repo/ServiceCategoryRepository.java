package com.handyhub.Itemservice.repo;

import com.handyhub.Itemservice.modal.ServiceCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceCategoryRepository extends JpaRepository<ServiceCategory, Long> {
    boolean existsByCategoryName(String categoryName);
}