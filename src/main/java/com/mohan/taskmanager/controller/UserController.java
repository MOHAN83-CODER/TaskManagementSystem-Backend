package com.mohan.taskmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.mohan.taskmanager.entity.User;
import com.mohan.taskmanager.service.UserService;

@RestController
@RequestMapping("/api/users")

public class UserController {

    @Autowired
    private UserService userService;

    // Signup API
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.registerUser(user);
    }

    // Login API
    @PostMapping("/login")
    public User login(@RequestBody User user) {
        return userService.login(user.getEmail(), user.getPassword());
 
    }
    // Forgot Password API
@PostMapping("/forgot-password")
public User forgotPassword(@RequestBody User user) {

    return userService.forgotPassword(
            user.getEmail(),
            user.getPassword()
    );
}
}