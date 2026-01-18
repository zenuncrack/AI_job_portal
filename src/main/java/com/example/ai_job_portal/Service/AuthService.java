package com.example.ai_job_portal.Service;

import com.example.ai_job_portal.Config.JwtService;
import com.example.ai_job_portal.Dto.AuthRequest;
import com.example.ai_job_portal.Dto.AuthResponse;
import com.example.ai_job_portal.Dto.RegisterRequest;
import com.example.ai_job_portal.Entity.UserEntity;
import com.example.ai_job_portal.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    // --- REGISTER LOGIC ---
    public AuthResponse register(RegisterRequest request) {

        // 1. Create User Object
        var user = new UserEntity();
        user.setFirstName(request.getFirstname());
        user.setLastName(request.getLastname());
        user.setEmail(request.getEmail());


        user.setPassword(passwordEncoder.encode(request.getPassword()));


        repository.save(user);


        var jwtToken = jwtService.generateToken(user);

        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }


    public AuthResponse authenticate(AuthRequest request) {


        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );


        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();


        var jwtToken = jwtService.generateToken(user);

        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }
}