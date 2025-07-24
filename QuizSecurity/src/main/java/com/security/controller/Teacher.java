package com.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Teacher {
    @GetMapping("/teacher")
    public String  teacher(){
        return "Teacher Page!";
    }
}
