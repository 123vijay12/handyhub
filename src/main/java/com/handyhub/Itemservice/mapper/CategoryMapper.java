package com.handyhub.Itemservice.mapper;

import com.handyhub.Itemservice.dto.ServiceCategoryDto;
import com.handyhub.Itemservice.modal.ServiceCategory;

public class CategoryMapper {

    public static ServiceCategoryDto toDto(ServiceCategory entity) {
        if (entity == null) return null;
        ServiceCategoryDto dto = new ServiceCategoryDto();
        dto.setCategoryId(entity.getCategoryId());
        dto.setCategoryName(entity.getCategoryName());
        dto.setCategoryDescription(entity.getCategoryDescription());
        dto.setImageUrl(entity.getImageUrl());
        return dto;
    }

    public static ServiceCategory toEntity(ServiceCategoryDto dto) {
        if (dto == null) return null;
        ServiceCategory entity = new ServiceCategory();
        entity.setCategoryId(dto.getCategoryId());
        entity.setCategoryName(dto.getCategoryName());
        entity.setCategoryDescription(dto.getCategoryDescription());
        //entity.setImageUrl(dto.getImageUrl());
        return entity;
    }
}
