package com.example.loginbackend.service;

import com.example.loginbackend.dto.LoginRequest;
import com.example.loginbackend.dto.LoginResponse;
import com.example.loginbackend.entity.User;
import com.example.loginbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    /**
     * Register a new user
     * @param loginRequest the login request containing email and password
     * @return LoginResponse with success status
     */
    public LoginResponse registerUser(LoginRequest loginRequest) {
        try {
            // Check if user already exists
            if (userRepository.existsByEmail(loginRequest.getEmail())) {
                return new LoginResponse(false, "User with this email already exists");
            }
            
            // Create new user
            User user = new User();
            user.setEmail(loginRequest.getEmail());
            user.setPassword(passwordEncoder.encode(loginRequest.getPassword()));
            user.setIsActive(true);
            
            // Save user to database
            User savedUser = userRepository.save(user);
            
            // Create user info for response
            LoginResponse.UserInfo userInfo = new LoginResponse.UserInfo(
                savedUser.getId(),
                savedUser.getEmail(),
                savedUser.getCreatedAt()
            );
            
            return new LoginResponse(true, "User registered successfully", null, userInfo);
            
        } catch (Exception e) {
            return new LoginResponse(false, "Error registering user: " + e.getMessage());
        }
    }
    
    /**
     * Authenticate user login
     * @param loginRequest the login request containing email and password
     * @return LoginResponse with authentication result
     */
    public LoginResponse authenticateUser(LoginRequest loginRequest) {
        try {
            // Find user by email
            Optional<User> userOptional = userRepository.findByEmailAndIsActive(
                loginRequest.getEmail(), true);
            
            if (userOptional.isEmpty()) {
                return new LoginResponse(false, "Invalid email or password");
            }
            
            User user = userOptional.get();
            
            // Verify password
            if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                return new LoginResponse(false, "Invalid email or password");
            }
            
            // Create user info for response
            LoginResponse.UserInfo userInfo = new LoginResponse.UserInfo(
                user.getId(),
                user.getEmail(),
                user.getCreatedAt()
            );
            
            return new LoginResponse(true, "Login successful", "jwt-token-placeholder", userInfo);
            
        } catch (Exception e) {
            return new LoginResponse(false, "Error during authentication: " + e.getMessage());
        }
    }
    
    /**
     * Get user by email
     * @param email the email address
     * @return Optional containing the user if found
     */
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    /**
     * Get all users (for admin purposes)
     * @return List of all users
     */
    public java.util.List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
