package com.handyhub.user.service;

import com.handyhub.user.dto.WorkerProfileDTO;

import java.util.List;

public interface WorkerProfileService {

    WorkerProfileDTO createWorkerProfile(WorkerProfileDTO dto);
    WorkerProfileDTO updateWorkerProfile(Long id, WorkerProfileDTO dto);
    void deleteWorkerProfile(Long id);
    WorkerProfileDTO getWorkerProfileById(Long id);
    List<WorkerProfileDTO> getAllWorkerProfiles();
    List<WorkerProfileDTO> getAvailableWorkers();
    List<WorkerProfileDTO> searchByArea(String area);
    List<WorkerProfileDTO> getWorkersBySubCategory(Long subCategoryId);

    List<WorkerProfileDTO> searchWorkers(Long subCategoryId, List<Long> skillIds,
                                         String serviceArea, int page, int size);

}
