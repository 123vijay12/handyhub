package com.handyhub.Itemservice.controller;

import com.handyhub.Itemservice.dto.ServiceCategoryDto;
import com.handyhub.Itemservice.service.ServiceCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/categories")
public class ServiceCategoryController {

    private final ServiceCategoryService service;

    public ServiceCategoryController(ServiceCategoryService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ServiceCategoryDto> create(@RequestBody ServiceCategoryDto dto) {
        ServiceCategoryDto created = service.createCategory(dto);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceCategoryDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<ServiceCategoryDto>> list() {
        return ResponseEntity.ok(service.listAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceCategoryDto> update(@PathVariable Long id,
                                                     @RequestBody ServiceCategoryDto dto) {
        return ResponseEntity.ok(service.updateCategory(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
