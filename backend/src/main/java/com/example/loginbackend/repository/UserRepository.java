package com.example.loginbackend.repository;

import com.example.loginbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    /**
     * Find user by email address
     * @param email the email address to search for
     * @return Optional containing the user if found, empty otherwise
     */
    Optional<User> findByEmail(String email);
    
    /**
     * Check if a user exists with the given email
     * @param email the email address to check
     * @return true if user exists, false otherwise
     */
    boolean existsByEmail(String email);
    
    /**
     * Find user by email and active status
     * @param email the email address to search for
     * @param isActive the active status
     * @return Optional containing the user if found, empty otherwise
     */
    Optional<User> findByEmailAndIsActive(String email, Boolean isActive);
}
