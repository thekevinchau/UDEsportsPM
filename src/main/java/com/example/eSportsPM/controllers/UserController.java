package com.example.eSportsPM.controllers;

import com.example.eSportsPM.DTOs.UserDTOs.UserCreationDTO;
import com.example.eSportsPM.DTOs.UserDTOs.UserDTO;
import com.example.eSportsPM.models.User;
import com.example.eSportsPM.services.UserServices.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login (@RequestBody User user){
        return userService.login(user);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserCreationDTO userInfo){
        return userService.register(userInfo);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String username){
        return userService.getUser(username);
    }

}
