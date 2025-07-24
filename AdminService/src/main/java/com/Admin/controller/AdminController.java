package com.Admin.controller;
import com.Admin.model.Users;
import com.Admin.repository.AdminRepository;
import com.Admin.service.AdminServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/admin")
public class AdminController
{
    @Autowired
    AdminServ adminService;
    @Autowired
    AdminRepository adminRepo;
    @GetMapping("/getall-users")
    public ResponseEntity<List<Users>> getAllUsers(){
        List<Users> users=adminRepo.findAll();
        return ResponseEntity.ok(users);
    }
    @GetMapping("/delete-user/{email}")
    public ResponseEntity<String> deleteUser(@PathVariable String email)
    {
        return adminService.deleteUser(email);
    }
    @GetMapping("/get-users-byrole/{role}")
    public ResponseEntity<List<Users>> getUsersByRole(@PathVariable String role)
    {
        List<Users> users = adminService.getUsersByRole(role);
        return ResponseEntity.ok(users);
    }
    @GetMapping("/updaterole/{email}/{newRole}")
    public ResponseEntity<String> updateRole(@PathVariable String email, @PathVariable String newRole)
    {
        return adminService.updateRole(email,newRole);
    }
}
