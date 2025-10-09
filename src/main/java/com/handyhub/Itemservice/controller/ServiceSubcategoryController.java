package com.handyhub.Itemservice.controller;

import com.handyhub.Itemservice.dto.ServiceSubcategoryDto;
import com.handyhub.Itemservice.service.ServiceSubcategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/service-subcategories")
public class ServiceSubcategoryController {

    private final ServiceSubcategoryService service;

    public ServiceSubcategoryController(ServiceSubcategoryService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ServiceSubcategoryDto> create(@RequestBody ServiceSubcategoryDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceSubcategoryDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<ServiceSubcategoryDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceSubcategoryDto> update(@PathVariable Long id,
                                                        @RequestBody ServiceSubcategoryDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ServiceSubcategoryDto>> getByCategoryId(@PathVariable Long categoryId) {
        return ResponseEntity.ok(service.listByCategory(categoryId));
    }


}
