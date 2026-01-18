package com.example.ai_job_portal.Controller;

import com.example.ai_job_portal.Dto.RecruiterDto;
import com.example.ai_job_portal.Service.RecruiterServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recruiter")
public class RecruiterControllers {

    private final RecruiterServiceInterface recruiterService;


    @PostMapping
    public ResponseEntity<RecruiterDto> createRecruiter(@RequestBody RecruiterDto recruiterDto) {
        return ResponseEntity.ok(recruiterService.createRecruiter(recruiterDto));
    }


    @GetMapping
    public ResponseEntity<List<RecruiterDto>> getAllRecruiters() {
        return ResponseEntity.ok(recruiterService.getAllRecruiters());
    }


    @GetMapping("/{id}")
    public ResponseEntity<RecruiterDto> getRecruiterById(@PathVariable Long id) {
        return ResponseEntity.ok(recruiterService.getRecruiterById(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<RecruiterDto> updateRecruiter(
            @PathVariable Long id,
            @RequestBody RecruiterDto recruiterDto
    ) {
        return ResponseEntity.ok(recruiterService.updateRecruiter(id, recruiterDto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRecruiter(@PathVariable Long id) {
        recruiterService.deleteRecruiter(id);
        return ResponseEntity.ok("Recruiter deleted successfully");
    }
}

