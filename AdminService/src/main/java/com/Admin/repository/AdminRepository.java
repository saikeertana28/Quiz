package com.Admin.repository;

import com.Admin.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AdminRepository extends JpaRepository<Users, Integer>
{
    ResponseEntity<String> deleteByEmail(String email);
   List<Users> getUsersByRole(String role);
   Users getUserByEmail(String email);

    Users findByEmail(String email);
}