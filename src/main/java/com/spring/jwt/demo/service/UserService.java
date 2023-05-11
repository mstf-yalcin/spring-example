package com.spring.jwt.demo.service;

import com.spring.jwt.demo.entity.User;
import com.spring.jwt.demo.exception.NotFoundException;
import com.spring.jwt.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User getByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User (" + email + ") not found"));
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public User getById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User (" + id + ") not found"));
    }
}
