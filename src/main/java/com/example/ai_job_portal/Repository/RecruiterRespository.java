package com.example.ai_job_portal.Repository;


import com.example.ai_job_portal.Entity.RecruiterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruiterRespository extends JpaRepository<RecruiterEntity,Long> {

    boolean existsByEmail(String email);
}
