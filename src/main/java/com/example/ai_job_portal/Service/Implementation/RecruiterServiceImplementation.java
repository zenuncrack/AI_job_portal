package com.example.ai_job_portal.Service.Implementation;

import com.example.ai_job_portal.Dto.RecruiterDto;
import com.example.ai_job_portal.Entity.RecruiterEntity;
import com.example.ai_job_portal.Repository.RecruiterRespository;
import com.example.ai_job_portal.Service.RecruiterServiceInterface;
import com.example.ai_job_portal.Service.RecruiterServiceInterface;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecruiterServiceImplementation implements RecruiterServiceInterface {

    private final RecruiterRespository recruiterRepository;
    private final ModelMapper modelMapper;

    @Override
    public RecruiterDto createRecruiter(RecruiterDto dto) {
        // 1. Check for Duplicate Email
        if (recruiterRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Recruiter email already exists: " + dto.getEmail());
        }

        // 2. Map DTO to Entity
        RecruiterEntity entity = modelMapper.map(dto, RecruiterEntity.class);

        // 3. Save to DB
        RecruiterEntity savedEntity = recruiterRepository.save(entity);
        return modelMapper.map(savedEntity, RecruiterDto.class);
    }

    @Override
    public List<RecruiterDto> getAllRecruiters() {
        return recruiterRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, RecruiterDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public RecruiterDto getRecruiterById(Long id) {
        RecruiterEntity entity = recruiterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recruiter not found with id: " + id));
        return modelMapper.map(entity, RecruiterDto.class);
    }

    @Override
    public RecruiterDto updateRecruiter(Long id, RecruiterDto newData) {
        RecruiterEntity existing = recruiterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recruiter not found"));

        // Smart Update: Only update fields that are not null
        if (newData.getFirstName() != null) existing.setFirstName(newData.getFirstName());
        if (newData.getLastName() != null) existing.setLastName(newData.getLastName());
        if (newData.getEmail() != null) existing.setEmail(newData.getEmail());
        if (newData.getPassword() != null) existing.setPassword(newData.getPassword());
        if (newData.getAddress() != null) existing.setAddress(newData.getAddress());

        // New Fields
        if (newData.getPhone() != null) existing.setPhone(newData.getPhone());
        if (newData.getCompany() != null) existing.setCompany(newData.getCompany());

        RecruiterEntity saved = recruiterRepository.save(existing);
        return modelMapper.map(saved, RecruiterDto.class);
    }

    @Override
    public void deleteRecruiter(Long id) {
        if (!recruiterRepository.existsById(id)) {
            throw new RuntimeException("Recruiter not found with id: " + id);
        }
        recruiterRepository.deleteById(id);
    }
}