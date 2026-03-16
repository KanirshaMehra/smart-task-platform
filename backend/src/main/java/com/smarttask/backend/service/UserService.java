package com.smarttask.backend.service;

import com.smarttask.backend.config.JwtUtil;
import com.smarttask.backend.config.SecurityConfig;
import com.smarttask.backend.dto.LoginRequest;
import com.smarttask.backend.dto.LoginResponse;
import com.smarttask.backend.dto.UserResponse;
import com.smarttask.backend.entity.User;
import com.smarttask.backend.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, SecurityConfig securityConfig, BCryptPasswordEncoder bCryptPasswordEncoder, BCryptPasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public UserResponse registerUser(User user){

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser=userRepository.save(user);

        return new UserResponse(savedUser.getId(),savedUser.getName(),savedUser.getEmail());
    }

    public LoginResponse loginUser(LoginRequest request){
        User user=userRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new RuntimeException("User not found"));
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid password");
        }

        String token= jwtUtil.generateToken(user.getEmail());
        return new LoginResponse(token);

    }
}
