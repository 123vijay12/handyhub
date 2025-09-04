package com.handyhub.Itemservice.service;

import com.handyhub.Itemservice.dto.ServiceCategoryDto;

import java.util.List;

public interface ServiceCategoryService {
    ServiceCategoryDto createCategory(ServiceCategoryDto dto);
    ServiceCategoryDto updateCategory(Long id, ServiceCategoryDto dto);
    ServiceCategoryDto getById(Long id);
    List<ServiceCategoryDto> listAll();
    void deleteById(Long id);
}