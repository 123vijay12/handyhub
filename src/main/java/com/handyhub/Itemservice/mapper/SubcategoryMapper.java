package com.handyhub.Itemservice.mapper;

import com.handyhub.Itemservice.dto.ServiceSubcategoryDto;
import com.handyhub.Itemservice.modal.ServiceCategory;
import com.handyhub.Itemservice.modal.ServiceSubcategory;

public class SubcategoryMapper {

    public static ServiceSubcategoryDto toDto(ServiceSubcategory entity) {
        if (entity == null) return null;
        ServiceSubcategoryDto dto = new ServiceSubcategoryDto();
        dto.setSubcategoryId(entity.getSubcategoryId());
        dto.setCategoryId(entity.getCategory() != null ? entity.getCategory().getCategoryId() : null);
        dto.setSubcategoryName(entity.getSubcategoryName());
        dto.setSubcategoryDescription(entity.getSubcategoryDescription());
        dto.setAverageDuration(entity.getAverageDuration());
        return dto;
    }

    public static ServiceSubcategory toEntity(ServiceSubcategoryDto dto) {
        if (dto == null) return null;
        ServiceSubcategory entity = new ServiceSubcategory();
        entity.setSubcategoryId(dto.getSubcategoryId());
        entity.setSubcategoryName(dto.getSubcategoryName());
        entity.setSubcategoryDescription(dto.getSubcategoryDescription());
        entity.setAverageDuration(dto.getAverageDuration());

        if (dto.getCategoryId() != null) {
            ServiceCategory cat = new ServiceCategory();
            cat.setCategoryId(dto.getCategoryId());
            entity.setCategory(cat);
        }
        return entity;
    }
}
