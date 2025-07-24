package com.student.dto;
import jakarta.persistence.*;
import java.time.LocalDate;
public class UsersDto{
    private String username;
    private LocalDate dob;
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
    public LocalDate getDob() {
        return dob;
    }
}
