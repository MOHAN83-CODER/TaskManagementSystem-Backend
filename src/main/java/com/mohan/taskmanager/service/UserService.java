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

        Optional<User> user = userRepository.findByEmail(email);

        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user.get();
        }

        throw new RuntimeException("Invalid Email or Password");
    }
    // Forgot Password
public User forgotPassword(String email, String password) {

    Optional<User> user = userRepository.findByEmail(email);

    if (user.isPresent()) {

        User existingUser = user.get();
        existingUser.setPassword(password);

        return userRepository.save(existingUser);
    }

    throw new RuntimeException("Email not found!");
}
}