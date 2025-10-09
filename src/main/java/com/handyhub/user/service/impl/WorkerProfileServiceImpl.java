package com.handyhub.user.service.impl;

import com.handyhub.Itemservice.modal.ServiceCategory;
import com.handyhub.Itemservice.modal.ServiceSubcategory;
import com.handyhub.Itemservice.repo.ServiceCategoryRepository;
import com.handyhub.Itemservice.repo.ServiceSubcategoryRepository;
import com.handyhub.shared.exception.custom.DuplicateResourceException;
import com.handyhub.shared.exception.custom.ResourceNotFoundException;
import com.handyhub.user.dto.UserDTO;
import com.handyhub.user.dto.WorkerProfileDTO;
import com.handyhub.user.mapper.UserMapper;
import com.handyhub.user.mapper.WorkerProfileMapper;
import com.handyhub.user.model.*;
import com.handyhub.user.repository.RoleRepository;
import com.handyhub.user.repository.SkillRepository;
import com.handyhub.user.repository.UserRepository;
import com.handyhub.user.repository.WorkerProfileRepository;
import com.handyhub.user.service.WorkerProfileService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class WorkerProfileServiceImpl implements WorkerProfileService {

    private final WorkerProfileRepository repository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final SkillRepository skillRepository;

    private final ServiceCategoryRepository serviceCategoryRepository;

    private final ServiceSubcategoryRepository serviceSubcategoryRepository;
    public WorkerProfileServiceImpl(WorkerProfileRepository repository,
                                    UserRepository userRepository,
                                    RoleRepository roleRepository,
                                    SkillRepository skillRepository,
                                    ServiceSubcategoryRepository serviceSubcategoryRepository,
                                    ServiceCategoryRepository serviceCategoryRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.skillRepository = skillRepository;
        this.serviceSubcategoryRepository=serviceSubcategoryRepository;
        this.serviceCategoryRepository=serviceCategoryRepository;
    }

    @Override
    @Transactional
    public WorkerProfileDTO createWorkerProfile(WorkerProfileDTO dto) {
        if (dto == null || dto.getUserDTO() == null) {
            throw new IllegalArgumentException("WorkerProfileDTO or UserDTO cannot be null");
        }

        UserDTO userDTO = dto.getUserDTO();
        if (userDTO.getEmail() == null || userDTO.getEmail().isEmpty()) {
            throw new IllegalArgumentException("User email cannot be null or empty");
        }

        if (userRepository.existsByContactInfoEmail(userDTO.getEmail())) {
            throw new DuplicateResourceException("Email already exists: " + userDTO.getEmail());
        }

        User userEntity = UserMapper.toEntity(userDTO, null);

        Role workerRole = roleRepository.findById(5L)
                .orElseThrow(() -> new ResourceNotFoundException("role", "id", 5L));
        userEntity.setRoles(Set.of(workerRole));

        User savedUser = userRepository.save(userEntity);

        WorkerProfile workerEntity = WorkerProfileMapper.toEntity(dto, savedUser);

        // Map skills
        if (dto.getSkills() != null && !dto.getSkills().isEmpty()) {
            Set<Skill> skills = dto.getSkills().stream()
                    .map(name -> skillRepository.findByNameIgnoreCase(name)
                            .orElseGet(() -> {
                                Skill newSkill = new Skill();
                                newSkill.setName(name); // create Skill properly
                                return skillRepository.save(newSkill);
                            })
                    )
                    .collect(Collectors.toSet());
            workerEntity.setSkills(skills);
        }

        ServiceSubcategory subcategory = serviceSubcategoryRepository
                .findById(dto.getSubCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Subcategory not found", "id", dto.getSubCategoryId()
                ));
           workerEntity.setServiceSubcategory(subcategory);

        ServiceCategory category = serviceCategoryRepository
                .findById(dto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Category not found", "id", dto.getCategoryId()
                ));
        workerEntity.setServiceSubcategory(subcategory);
        workerEntity.setServiceCategory(category);

        WorkerProfile savedWorker = repository.save(workerEntity);
        UserDTO savedUserDto = UserMapper.toDto(savedUser);
        return WorkerProfileMapper.toDTO(savedWorker, savedUserDto);
    }

    @Override
    @Transactional
    public WorkerProfileDTO updateWorkerProfile(Long id, WorkerProfileDTO dto) {
        if (dto == null || dto.getUserDTO() == null) {
            throw new IllegalArgumentException("WorkerProfileDTO or UserDTO cannot be null");
        }

        WorkerProfile existingWorker = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("WorkerProfile", "id", id));

        User existingUser = existingWorker.getUser();

        // Check email uniqueness
        UserDTO userDTO = dto.getUserDTO();
        userRepository.findByContactInfo_Email(userDTO.getEmail())
                .filter(u -> !u.getId().equals(existingUser.getId()))
                .ifPresent(u -> { throw new DuplicateResourceException("Email already exists: " + userDTO.getEmail()); });

        // Update User fields
        existingUser.setUsername(userDTO.getUsername());
        existingUser.setFirstName(userDTO.getFirstName());
        existingUser.setLastName(userDTO.getLastName());
        existingUser.setActive(userDTO.isActive());

        if (existingUser.getBioData() == null) existingUser.setBioData(new BioData());
        BioData bio = existingUser.getBioData();
        bio.setGender(userDTO.getGender());
        bio.setDateOfBirth(userDTO.getDateOfBirth());
        bio.setNationality(userDTO.getNationality());
        bio.setProfilePictureUrl(userDTO.getProfilePictureUrl());

        if (existingUser.getContactInfo() == null) existingUser.setContactInfo(new ContactInfo());
        ContactInfo contact = existingUser.getContactInfo();
        contact.setPhone(userDTO.getPhone());
        contact.setEmail(userDTO.getEmail());
        contact.setAddress(userDTO.getAddress());
        contact.setCity(userDTO.getCity());
        contact.setState(userDTO.getState());
        contact.setCountry(userDTO.getCountry());

        if (existingUser.getAuditInfo() == null) existingUser.setAuditInfo(new AuditInfo());
        AuditInfo audit = existingUser.getAuditInfo();
        audit.setUpdatedBy(userDTO.getUpdatedBy());
        audit.setUpdatedDate(userDTO.getUpdatedDate());

        User savedUser = userRepository.save(existingUser);

        // Update WorkerProfile fields
        existingWorker.setExperience(dto.getExperience());
        existingWorker.setHourlyRate(dto.getHourlyRate());
        existingWorker.setServiceArea(dto.getServiceArea());
        existingWorker.setAvailable(dto.isAvailable());

        // Update skills
        if (dto.getSkills() != null && !dto.getSkills().isEmpty()) {
            Set<Skill> skills = dto.getSkills().stream()
                    .map(name -> skillRepository.findByNameIgnoreCase(name)
                            .orElseGet(() -> {
                                Skill newSkill = new Skill();
                                newSkill.setName(name); // create Skill properly
                                return skillRepository.save(newSkill);
                            })
                    )
                    .collect(Collectors.toSet());
            existingWorker.setSkills(skills);
        }

        ServiceSubcategory subcategory = serviceSubcategoryRepository
                .findById(dto.getSubCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Subcategory not found", "id", dto.getSubCategoryId()
                ));
        ServiceCategory category = serviceCategoryRepository
                .findById(dto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Category not found", "id", dto.getCategoryId()
                ));

        existingWorker.setServiceSubcategory(subcategory);
        existingWorker.setServiceCategory(category);
        WorkerProfile savedWorker = repository.save(existingWorker);
        UserDTO savedUserDto = UserMapper.toDto(savedUser);
        return WorkerProfileMapper.toDTO(savedWorker, savedUserDto);
    }

    @Override
    @Transactional
    public void deleteWorkerProfile(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("WorkerProfile", "id", id);
        }
        repository.deleteById(id);
    }

    @Override
    public WorkerProfileDTO getWorkerProfileById(Long id) {
        WorkerProfile worker = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("WorkerProfile", "id", id));
        return WorkerProfileMapper.toDTO(worker, UserMapper.toDto(worker.getUser()));
    }

    @Override
    public List<WorkerProfileDTO> getAllWorkerProfiles() {
        return repository.findAll().stream()
                .map(w -> WorkerProfileMapper.toDTO(w, UserMapper.toDto(w.getUser())))
                .collect(Collectors.toList());
    }

    @Override
    public List<WorkerProfileDTO> getAvailableWorkers() {
        return repository.findByAvailableTrue().stream()
                .map(w -> WorkerProfileMapper.toDTO(w, UserMapper.toDto(w.getUser())))
                .collect(Collectors.toList());
    }

    @Override
    public List<WorkerProfileDTO> searchByArea(String area) {
        return repository.findByServiceAreaContainingIgnoreCase(area).stream()
                .map(w -> WorkerProfileMapper.toDTO(w, UserMapper.toDto(w.getUser())))
                .collect(Collectors.toList());
    }

    @Override
    public List<WorkerProfileDTO> getWorkersBySubCategory(Long subCategoryId) {
        return repository.findByServiceSubcategory_SubcategoryId(subCategoryId).stream()
                .map(w -> WorkerProfileMapper.toDTO(w, UserMapper.toDto(w.getUser())))
                .collect(Collectors.toList());
    }

    @Override
    public List<WorkerProfileDTO> getWorkersByCategory(Long categoryId) {
        return repository.findByServiceCategory_CategoryId(categoryId).stream()
                .map(w -> WorkerProfileMapper.toDTO(w, UserMapper.toDto(w.getUser())))
                .collect(Collectors.toList());
    }

    @Override
    public List<WorkerProfileDTO> searchWorkers(Long subCategoryId, List<Long> skillIds, String serviceArea, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        List<WorkerProfile> workers = repository.searchWorkers(subCategoryId, skillIds, serviceArea, pageable);
        return workers.stream()
                .map(w -> WorkerProfileMapper.toDTO(w, UserMapper.toDto(w.getUser())))
                .collect(Collectors.toList());
    }
}
