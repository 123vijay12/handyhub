package com.handyhub.Itemservice.serviceimp;

import com.handyhub.Itemservice.dto.ServiceSubcategoryDto;
import com.handyhub.Itemservice.mapper.SubcategoryMapper;
import com.handyhub.Itemservice.modal.ServiceCategory;
import com.handyhub.Itemservice.modal.ServiceSubcategory;
import com.handyhub.Itemservice.repo.ServiceCategoryRepository;
import com.handyhub.Itemservice.repo.ServiceSubcategoryRepository;
import com.handyhub.Itemservice.service.ServiceSubcategoryService;
import com.handyhub.shared.exception.custom.EntityNotFound;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ServiceSubcategoryServiceImpl implements ServiceSubcategoryService {

    private final ServiceSubcategoryRepository subRepo;
    private final ServiceCategoryRepository catRepo;

    public ServiceSubcategoryServiceImpl(ServiceSubcategoryRepository subRepo, ServiceCategoryRepository catRepo) {
        this.subRepo = subRepo;
        this.catRepo = catRepo;
    }

    @Override
    public ServiceSubcategoryDto create(ServiceSubcategoryDto dto) {
        // ensure category exists
        ServiceCategory cat = catRepo.findById(dto.getCategoryId())
                .orElseThrow(() -> new EntityNotFound("Category not found: " + dto.getCategoryId()));

        ServiceSubcategory ent = SubcategoryMapper.toEntity(dto);
        ent.setCategory(cat);
        ServiceSubcategory saved = subRepo.save(ent);
        return SubcategoryMapper.toDto(saved);
    }

    @Override
    public ServiceSubcategoryDto update(Long id, ServiceSubcategoryDto dto) {
        ServiceSubcategory exist = subRepo.findById(id)
                .orElseThrow(() -> new EntityNotFound("Subcategory not found: " + id));

        exist.setSubcategoryName(dto.getSubcategoryName());
        exist.setSubcategoryDescription(dto.getSubcategoryDescription());
        exist.setAverageDuration(dto.getAverageDuration());
        // optionally change parent category
        if (dto.getCategoryId() != null && !dto.getCategoryId().equals(exist.getCategory().getCategoryId())) {
            ServiceCategory newCat = catRepo.findById(dto.getCategoryId())
                    .orElseThrow(() -> new EntityNotFound("Category not found: " + dto.getCategoryId()));
            exist.setCategory(newCat);
        }
        ServiceSubcategory saved = subRepo.save(exist);
        return SubcategoryMapper.toDto(saved);
    }

    @Override
    public ServiceSubcategoryDto getById(Long id) {
        return subRepo.findById(id).map(SubcategoryMapper::toDto)
                .orElseThrow(() -> new EntityNotFound("Subcategory not found: " + id));
    }

    @Override
    public List<ServiceSubcategoryDto> getAll() {
        return subRepo.findAll().stream()
                .map(SubcategoryMapper::toDto)
                .collect(Collectors.toList());
    }



    @Override
    public List<ServiceSubcategoryDto> listByCategory(Long categoryId) {
        return subRepo.findByCategoryCategoryId(categoryId).stream()
                .map(SubcategoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        subRepo.deleteById(id);
    }
}
