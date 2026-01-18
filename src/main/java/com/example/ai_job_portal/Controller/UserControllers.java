package com.example.ai_job_portal.Controller;


import com.example.ai_job_portal.Dto.UserDto;
import com.example.ai_job_portal.Service.UserServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserControllers {


    private final UserServiceInterface userServiceInterface;


    @GetMapping("/all_users")
    public List<UserDto> getAllUsers(){
        return userServiceInterface.getAllUsers();
    }

    @PostMapping(value = "/insert_user", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<UserDto> addNewUser(
            @RequestParam("firstname") String firstname,
            @RequestParam("lastname") String lastname,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("address") String address,
            @RequestParam("cvFile") MultipartFile cvFile
    ) throws IOException {
        userServiceInterface.addNewUser(firstname, lastname, email, password, address, cvFile);

        return userServiceInterface.getAllUsers();
    }

    @PutMapping(value = "/update_user/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public UserDto updateUser(
            @PathVariable Long id,
            @ModelAttribute UserDto userDto,
            @RequestParam(value = "cvFile", required = false) MultipartFile cvFile
    ) throws IOException {


        return userServiceInterface.updateUser(id, userDto, cvFile);
    }

    @DeleteMapping("/delete_user/{id}")
    public String deleteUser(@PathVariable Long id) {
        userServiceInterface.deleteUser(id);
        return "User deleted successfully";
    }


}
