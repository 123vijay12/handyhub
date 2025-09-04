package com.handyhub.Itemservice.service;

import com.handyhub.Itemservice.dto.ServiceSubcategoryDto;

import java.util.List;

public interface ServiceSubcategoryService {
    ServiceSubcategoryDto create(ServiceSubcategoryDto dto);
    ServiceSubcategoryDto update(Long id, ServiceSubcategoryDto dto);
    ServiceSubcategoryDto getById(Long id);
    List<ServiceSubcategoryDto> getAll();
    List<ServiceSubcategoryDto> listByCategory(Long categoryId);
    void delete(Long id);
}
