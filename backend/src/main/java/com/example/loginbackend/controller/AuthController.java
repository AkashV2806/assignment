package com.example.loginbackend.controller;

import com.example.loginbackend.dto.LoginRequest;
import com.example.loginbackend.dto.LoginResponse;
import com.example.loginbackend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // Allow CORS for frontend integration
public class AuthController {
    
    @Autowired
    private UserService userService;
    
    /**
     * User login endpoint
     * @param loginRequest the login request containing email and password
     * @return ResponseEntity with login response
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse response = userService.authenticateUser(loginRequest);
        
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
    
    /**
     * User registration endpoint
     * @param loginRequest the registration request containing email and password
     * @return ResponseEntity with registration response
     */
    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse response = userService.registerUser(loginRequest);
        
        if (response.isSuccess()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    
    /**
     * Health check endpoint
     * @return ResponseEntity with health status
     */
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Auth service is running");
    }
}
