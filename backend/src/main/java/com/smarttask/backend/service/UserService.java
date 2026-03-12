package com.smarttask.backend.service;

import com.smarttask.backend.dto.UserResponse;
import com.smarttask.backend.entity.User;
import com.smarttask.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse registerUser(User user){

        User savedUser=userRepository.save(user);

        return new UserResponse(savedUser.getId(),savedUser.getName(),savedUser.getEmail());
    }
}
