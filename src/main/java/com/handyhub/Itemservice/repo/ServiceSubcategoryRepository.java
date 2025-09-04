package com.handyhub.Itemservice.repo;

import com.handyhub.Itemservice.modal.ServiceSubcategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceSubcategoryRepository extends JpaRepository<ServiceSubcategory, Long> {
    List<ServiceSubcategory> findByCategoryCategoryId(Long categoryId);

}