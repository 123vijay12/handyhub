package com.handyhub.Itemservice.serviceimp;

import com.handyhub.Itemservice.dto.ServiceCategoryDto;
import com.handyhub.Itemservice.mapper.CategoryMapper;
import com.handyhub.Itemservice.modal.ServiceCategory;
import com.handyhub.Itemservice.repo.ServiceCategoryRepository;
import com.handyhub.Itemservice.service.ServiceCategoryService;
import com.handyhub.shared.exception.custom.BadRequestException;
import com.handyhub.shared.exception.custom.ConflictException;
import com.handyhub.shared.exception.custom.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ServiceCategoryServiceImpl implements ServiceCategoryService {

    private final ServiceCategoryRepository repository;

    public ServiceCategoryServiceImpl(ServiceCategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public ServiceCategoryDto createCategory(ServiceCategoryDto dto) {
        if (repository.existsByCategoryName(dto.getCategoryName())) {
            throw new IllegalArgumentException("Category with same name already exists");
        }
        ServiceCategory ent = CategoryMapper.toEntity(dto);
        ServiceCategory saved = repository.save(ent);
        return CategoryMapper.toDto(saved);
    }

    @Override
    public ServiceCategoryDto updateCategory(Long id, ServiceCategoryDto dto) {
        ServiceCategory exist = repository.findById(id)
                .orElseThrow(() -> new BadRequestException("Category not found: " + id));
        exist.setCategoryName(dto.getCategoryName());
        exist.setCategoryDescription(dto.getCategoryDescription());
        ServiceCategory saved = repository.save(exist);
        return CategoryMapper.toDto(saved);
    }

    @Override
    public ServiceCategoryDto getById(Long id) {
        return repository.findById(id).map(CategoryMapper::toDto)
                .orElseThrow(() -> new BadRequestException("Category not found: " + id));
    }

    @Override
    public List<ServiceCategoryDto> listAll() {
        return repository.findAll()
                .stream()
                .map(CategoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}