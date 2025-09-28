package com.handyhub.user.repository;

import com.handyhub.user.model.WorkerProfile;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkerProfileRepository extends JpaRepository<WorkerProfile, Long> {

    // Find WorkerProfile by linked UserId
    WorkerProfile findByUserId(Long userId);

    // Find all available workers
    List<WorkerProfile> findByAvailableTrue();

    // Search workers by service area
    List<WorkerProfile> findByServiceAreaContainingIgnoreCase(String area);

    // Find by SubCategory
    List<WorkerProfile> findByServiceSubcategory_SubcategoryId(Long subCategoryId);

    // Search by subcategory, skills, service area with pagination
    @Query("SELECT DISTINCT w FROM WorkerProfile w JOIN w.skills s " +
            "WHERE (:subCategoryId IS NULL OR w.serviceSubcategory.id = :subCategoryId) " +
            "AND (:serviceArea IS NULL OR LOWER(w.serviceArea) LIKE LOWER(CONCAT('%', :serviceArea, '%'))) " +
            "AND (:skillIds IS NULL OR s.id IN :skillIds)")
    List<WorkerProfile> searchWorkers(
            @Param("subCategoryId") Long subCategoryId,
            @Param("skillIds") List<Long> skillIds,
            @Param("serviceArea") String serviceArea,
            Pageable pageable
    );
}
