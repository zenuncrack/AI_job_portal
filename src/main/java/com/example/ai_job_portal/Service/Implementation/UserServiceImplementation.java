package com.example.ai_job_portal.Service.Implementation;

import com.example.ai_job_portal.Dto.UserDto;
import com.example.ai_job_portal.Entity.UserEntity;
import com.example.ai_job_portal.Repository.UserRepository;
import com.example.ai_job_portal.Service.UserServiceInterface;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
//
public class UserServiceImplementation implements UserServiceInterface {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<UserDto> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();

        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void addNewUser(String firstname, String lastname, String email, String password, String address, MultipartFile cvFile) throws IOException {


        if (userRepository.existsByEmail(email)) {

            throw new RuntimeException("Error: Email is already in use!");
        } else {

            String uploadDir = System.getProperty("user.dir") + "/Uploads/UserCVs/";

            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }


            String originalFilename = cvFile.getOriginalFilename();
            String filePath = uploadDir + originalFilename;


            cvFile.transferTo(new File(filePath));


            UserEntity newUser = new UserEntity();
            newUser.setFirstName(firstname);
            newUser.setLastName(lastname);
            newUser.setEmail(email);
            newUser.setPassword(password);
            newUser.setAddress(address);
            newUser.setCvFilePath(filePath);

            userRepository.save(newUser);
        }
    }

    @Override
    public UserDto updateUser(Long id, UserDto newData, MultipartFile cvFile) throws IOException {


        UserEntity existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));


        if (newData.getFirstName() != null) existingUser.setFirstName(newData.getFirstName());
        if (newData.getLastName() != null)  existingUser.setLastName(newData.getLastName());
        if (newData.getEmail() != null)     existingUser.setEmail(newData.getEmail());
        if (newData.getPassword() != null)  existingUser.setPassword(newData.getPassword());
        if (newData.getAddress() != null)   existingUser.setAddress(newData.getAddress());


        if (cvFile != null && !cvFile.isEmpty()) {


            String oldFilePath = existingUser.getCvFilePath();
            if (oldFilePath != null) {
                Files.deleteIfExists(Paths.get(oldFilePath));
            }


            String uploadDir = System.getProperty("user.dir") + "/Uploads/UserCVs/";
            if (!Files.exists(Paths.get(uploadDir))) {
                Files.createDirectories(Paths.get(uploadDir));
            }

            String originalFilename = cvFile.getOriginalFilename();
            Path newFilePath = Paths.get(uploadDir, originalFilename);
            Files.copy(cvFile.getInputStream(), newFilePath, StandardCopyOption.REPLACE_EXISTING);


            existingUser.setCvFilePath(newFilePath.toString());
        }


        UserEntity savedUser = userRepository.save(existingUser);
        return modelMapper.map(savedUser, UserDto.class);
    }


    @Override
    public void deleteUser(Long id) {

        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));


        String filePath = user.getCvFilePath();
        if (filePath != null && !filePath.isEmpty()) {
            try {
                Path path = Paths.get(filePath);
                Files.deleteIfExists(path);
            } catch (IOException e) {
                System.err.println("Could not delete file: " + filePath);
            }
        }
        userRepository.delete(user);
    }
}