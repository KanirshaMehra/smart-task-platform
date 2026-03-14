package com.smarttask.backend.service;

import com.smarttask.backend.config.SecurityConfig;
import com.smarttask.backend.dto.UserResponse;
import com.smarttask.backend.entity.User;
import com.smarttask.backend.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, SecurityConfig securityConfig, BCryptPasswordEncoder bCryptPasswordEncoder, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse registerUser(User user){

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser=userRepository.save(user);

        return new UserResponse(savedUser.getId(),savedUser.getName(),savedUser.getEmail());
    }
}
