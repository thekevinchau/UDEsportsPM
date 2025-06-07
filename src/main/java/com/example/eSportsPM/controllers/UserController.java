package com.example.eSportsPM.controllers;

import com.example.eSportsPM.DTOs.Organization.PrimaryProfileReq;
import com.example.eSportsPM.DTOs.UserCreation;
import com.example.eSportsPM.DTOs.UserDTO;
import com.example.eSportsPM.models.User;
import com.example.eSportsPM.services.UserServices.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login (@RequestBody User user) throws BadRequestException {
        return userService.login(user);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody UserCreation userInfo){
        return userService.register(userInfo);
    }

    /*
    @GetMapping("/verify")
    //Send out a verification link to the user's email, set timeout for 15 minutes. If they click on the link within 15 minutes, it sends out
    // a GET Request to this endpoint, query the user DB for this token, and if they match, we can verify the user
    public String verifyRegistration(@RequestParam String token){

    }

     */

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String username){
        return userService.getUser(username);
    }

    @PatchMapping("/primary-profile")
    public ResponseEntity<String> setPrimaryProfile(@RequestBody PrimaryProfileReq req){
        return userService.setPrimaryProfile(req.getNewId());
    }

}
