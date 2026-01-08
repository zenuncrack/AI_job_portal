package com.example.ai_job_portal.Dto;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;

    private String firstName;
    private String lastName;

    private String email;
    private String password;
    private String address;

    private String cvFilePath;


}
