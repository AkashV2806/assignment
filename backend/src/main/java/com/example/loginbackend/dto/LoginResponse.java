package com.example.loginbackend.dto;

import java.time.LocalDateTime;

public class LoginResponse {
    
    private boolean success;
    private String message;
    private String token;
    private UserInfo user;
    private LocalDateTime timestamp;
    
    // Constructors
    public LoginResponse() {
        this.timestamp = LocalDateTime.now();
    }
    
    public LoginResponse(boolean success, String message) {
        this();
        this.success = success;
        this.message = message;
    }
    
    public LoginResponse(boolean success, String message, String token, UserInfo user) {
        this(success, message);
        this.token = token;
        this.user = user;
    }
    
    // Getters and Setters
    public boolean isSuccess() {
        return success;
    }
    
    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getToken() {
        return token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
    
    public UserInfo getUser() {
        return user;
    }
    
    public void setUser(UserInfo user) {
        this.user = user;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    
    // Inner class for user information
    public static class UserInfo {
        private Long id;
        private String email;
        private LocalDateTime createdAt;
        
        public UserInfo() {
        }
        
        public UserInfo(Long id, String email, LocalDateTime createdAt) {
            this.id = id;
            this.email = email;
            this.createdAt = createdAt;
        }
        
        // Getters and Setters
        public Long getId() {
            return id;
        }
        
        public void setId(Long id) {
            this.id = id;
        }
        
        public String getEmail() {
            return email;
        }
        
        public void setEmail(String email) {
            this.email = email;
        }
        
        public LocalDateTime getCreatedAt() {
            return createdAt;
        }
        
        public void setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
        }
    }
}
