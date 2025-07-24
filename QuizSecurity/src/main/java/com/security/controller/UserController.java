package com.security.controller;
import com.security.model.Users;
import com.security.repository.UserRepo;
import com.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.security.service.JwtUserService;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UserController {
    @Autowired
    private UserService userservice;
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Users user){
        return userservice.register(user);
    }
    @PostMapping("/login")
    public String login(@RequestBody Users user) {
        return userservice.verify(user);
    }
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        return userservice.forgotPassword(email);
    }
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestParam String newPassword,@RequestParam String confirmPassword) {
            if (!newPassword.equals(confirmPassword)) {
                return ResponseEntity.badRequest().body("Passwords do not match.");
            }
       return userservice.resetPassword(token, newPassword);
    }
    @PutMapping("/update-profile-pic/{email}")
    public ResponseEntity<String> updateProfilePic(
            @PathVariable String email,
            @RequestParam("file") MultipartFile file) {
        return userservice.updateProfilePicture(email, file);
    }
    @PutMapping("/update-profile/{email}")
    public ResponseEntity<String> updateUserProfile(
            @PathVariable String email,
            @RequestBody Users updatedData) {
        return userservice.updateUserProfile(email, updatedData);
    }
}
