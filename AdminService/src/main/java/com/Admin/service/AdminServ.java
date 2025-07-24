package com.Admin.service;

import com.Admin.model.Users;
import com.Admin.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.Admin.model.Users;
import com.Admin.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
    public class AdminServ {
        @Autowired
        AdminRepository adminRepo;
        public ResponseEntity<String> deleteUser(String email){
            Users user = adminRepo.findByEmail(email);
            if (user==null) {
                return ResponseEntity.ok("User Deleted");
            }
            adminRepo.delete(user);
            return ResponseEntity.ok("User Deleted");
        }
    public ResponseEntity<String> updateRole(String email, String newRole) {
        Users user = adminRepo.findByEmail(email);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        user.setRole(newRole);
        adminRepo.save(user);
        return ResponseEntity.ok("Role updated to " + user.getRole());
    }
    public ResponseEntity<List<Users>> getAllUsers(){
            List<Users> users = adminRepo.findAll();
            return ResponseEntity.ok(users);
    }
    public List<Users> getUsersByRole(String role){
            List<Users> users = adminRepo.getUsersByRole(role);
            return users;
    }
    }