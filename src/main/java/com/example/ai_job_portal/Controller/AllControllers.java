package com.example.ai_job_portal.Controller;


import com.example.ai_job_portal.Dto.UserDto;
import com.example.ai_job_portal.Service.ServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@RequiredArgsConstructor
public class AllControllers {


    private final ServiceInterface serviceInterface;


    @GetMapping("/all_users")
    public List<UserDto> getAllUsers(){
        return serviceInterface.getAllUsers();
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
        serviceInterface.addNewUser(firstname, lastname, email, password, address, cvFile);

        return serviceInterface.getAllUsers();
    }

    @PutMapping("/update_user/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        return serviceInterface.updateUser(id, userDto);
    }

    @DeleteMapping("/delete_user/{id}")
    public String deleteUser(@PathVariable Long id) {
        serviceInterface.deleteUser(id);
        return "User deleted successfully";
    }


}
