package com.mohan.taskmanager.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mohan.taskmanager.entity.User;
import com.mohan.taskmanager.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Signup
    public User registerUser(User user) {

        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser.isPresent()) {
            throw new RuntimeException("Email already exists!");
        }

        return userRepository.save(user);
    }

    // Login
    public User login(String email, String password) {

        System.out.println("=================================");
        System.out.println("LOGIN REQUEST");
        System.out.println("Email Received    : [" + email + "]");
        System.out.println("Password Received : [" + password + "]");

        Optional<User> user = userRepository.findByEmail(email);

        System.out.println("User Found : " + user.isPresent());

        if (user.isPresent()) {

            System.out.println("Password in DB : [" + user.get().getPassword() + "]");

            if (user.get().getPassword().equals(password)) {
                System.out.println("LOGIN SUCCESS");
                System.out.println("=================================");
                return user.get();
            } else {
                System.out.println("PASSWORD DOES NOT MATCH");
            }

        } else {
            System.out.println("EMAIL NOT FOUND");
        }

        System.out.println("LOGIN FAILED");
        System.out.println("=================================");

        throw new RuntimeException("Invalid Email or Password");
    }

    // Forgot Password
   // Forgot Password
// Forgot Password
// Forgot Password
public User forgotPassword(String email, String password) {

    System.out.println("========== FORGOT PASSWORD ==========");
    System.out.println("Email Received : [" + email + "]");
    System.out.println("Password       : [" + password + "]");

    Optional<User> user = userRepository.findByEmail(email);

    System.out.println("User Found : " + user.isPresent());

    if (user.isPresent()) {

        System.out.println("Email in DB : " + user.get().getEmail());

        User existingUser = user.get();
        existingUser.setPassword(password);

        userRepository.save(existingUser);

        System.out.println("PASSWORD UPDATED SUCCESSFULLY");
        System.out.println("===================================");

        return existingUser;
    }

    System.out.println("EMAIL NOT FOUND");
    System.out.println("===================================");

    throw new RuntimeException("Email not found!");
}
}