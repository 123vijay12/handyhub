package com.handyhub.user.controller;

import com.handyhub.user.dto.WorkerProfileDTO;
import com.handyhub.user.service.WorkerProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/worker-profiles")
public class WorkerProfileController {

    private final WorkerProfileService service;

    public WorkerProfileController(WorkerProfileService service) {
        this.service = service;
    }

    // Create
    @PostMapping
    public ResponseEntity<WorkerProfileDTO> createWorkerProfile(@RequestBody WorkerProfileDTO dto) {
        return ResponseEntity.ok(service.createWorkerProfile(dto));
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<WorkerProfileDTO> updateWorkerProfile(@PathVariable Long id,
                                                                @RequestBody WorkerProfileDTO dto) {
        return ResponseEntity.ok(service.updateWorkerProfile(id, dto));
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkerProfile(@PathVariable Long id) {
        service.deleteWorkerProfile(id);
        return ResponseEntity.noContent().build();
    }

    // Get by Id
    @GetMapping("/{id}")
    public ResponseEntity<WorkerProfileDTO> getWorkerProfileById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getWorkerProfileById(id));
    }

    // Get all
    @GetMapping
    public ResponseEntity<List<WorkerProfileDTO>> getAllWorkerProfiles() {
        return ResponseEntity.ok(service.getAllWorkerProfiles());
    }

    // Get available workers
    @GetMapping("/available")
    public ResponseEntity<List<WorkerProfileDTO>> getAvailableWorkers() {
        return ResponseEntity.ok(service.getAvailableWorkers());
    }

    // Search by area
    @GetMapping("/search/area")
    public ResponseEntity<List<WorkerProfileDTO>> searchByArea(@RequestParam String area) {
        return ResponseEntity.ok(service.searchByArea(area));
    }

    @GetMapping("/subcategory/{id}")
    public ResponseEntity<List<WorkerProfileDTO>> getWorkersBySubCategory(@PathVariable Long id) {
        return ResponseEntity.ok(service.getWorkersBySubCategory(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<WorkerProfileDTO>> searchWorkers(
            @RequestParam(required = false) Long subCategoryId,
            @RequestParam(required = false) List<Long> skillIds,
            @RequestParam(required = false) String location,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(service.searchWorkers(subCategoryId, skillIds, location, page, size));
    }

}
