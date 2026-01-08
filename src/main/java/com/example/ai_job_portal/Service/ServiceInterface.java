package com.example.ai_job_portal.Service;

import com.example.ai_job_portal.Dto.UserDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ServiceInterface {


    List<UserDto> getAllUsers();

    void addNewUser(String first, String last, String email, String pass, String addr, MultipartFile file) throws IOException;

    UserDto updateUser(Long id, UserDto userDto);

    void deleteUser(Long id);
}
