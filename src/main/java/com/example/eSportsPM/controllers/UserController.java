package com.example.eSportsPM.controllers;

import com.example.eSportsPM.models.User;
import com.example.eSportsPM.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login (@RequestBody User user){
        return userService.login(user);
    }
}
