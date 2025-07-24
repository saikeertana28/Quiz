package com.security.service;
import com.security.model.Users;
import com.security.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UserService {
    @Autowired
    private UserRepo repo;
    @Autowired
    private JwtUserService jwtservice;
    @Autowired
    private MailService mailService;
    @Autowired
    private AuthenticationManager authManager;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public ResponseEntity<String> register(Users user) {
        Users existingUser = repo.findByEmail(user.getEmail());
        if (existingUser != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already registered");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);
        return ResponseEntity.ok("User registered successfully");
    }
    public String verify(Users user) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

        if (authentication.isAuthenticated()) {
            Users fullUser = repo.findByEmail(user.getEmail());
            String token= jwtservice.generateToken(fullUser);
            return token;
        } else {
            return "failure";
        }
    }
    public ResponseEntity<String> forgotPassword( String email) {
        Users user = repo.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        String token = jwtservice.generateResetToken(email);
        mailService.sendResetLink(email, token);
        return ResponseEntity.ok("Reset password link sent to your email");
    }
    public ResponseEntity<String> resetPassword(String token, String newPassword) {
        String email = jwtservice.extractUserName(token);
        Users user = repo.findByEmail(email);
        if (user == null || jwtservice.isTokenExpired(token)) {
            return ResponseEntity.badRequest().body("Invalid or expired token.");
        }
        user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
        repo.save(user);
        return ResponseEntity.ok("Password reset successfully.");
    }
    public ResponseEntity<String> updateProfilePicture(String email, MultipartFile file) {
        Users user = repo.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        try {
            user.setProfilePic(file.getBytes());
            repo.save(user);
            return ResponseEntity.ok("Profile picture updated successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading profile picture");
        }
    }
    public ResponseEntity<String> updateUserProfile(String email, Users updatedData) {
        Users user = repo.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        if (updatedData.getUsername() != null) {
            user.setUsername(updatedData.getUsername());
        }
        if (updatedData.getDob() != null) {
            user.setDob(updatedData.getDob());
        }
        repo.save(user);
        return ResponseEntity.ok("Profile updated successfully");
    }
}
