package com.smarttask.backend.controller;

import com.smarttask.backend.dto.LoginRequest;
import com.smarttask.backend.dto.LoginResponse;
import com.smarttask.backend.dto.UserResponse;
import com.smarttask.backend.entity.User;
import com.smarttask.backend.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
@PostMapping("/register")
    public UserResponse registerUser(@RequestBody User user){
    System.out.println("Received user: " + user.getName());
    System.out.println("Email: " + user.getEmail());

        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest){
        return userService.loginUser(loginRequest);
    }
}
