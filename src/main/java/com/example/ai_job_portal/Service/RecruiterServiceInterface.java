package com.example.ai_job_portal.Service;

import com.example.ai_job_portal.Dto.RecruiterDto;
import java.util.*;

public interface RecruiterServiceInterface {
    RecruiterDto createRecruiter(RecruiterDto recruiterDto);
    List<RecruiterDto> getAllRecruiters();
    RecruiterDto getRecruiterById(Long id);
    RecruiterDto updateRecruiter(Long id, RecruiterDto recruiterDto);
    void deleteRecruiter(Long id);
}
