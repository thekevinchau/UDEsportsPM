package com.example.eSportsPM.controllers;

import com.example.eSportsPM.DTOs.UserDTOs.UserProfileDTO;
import com.example.eSportsPM.models.UserProfile;
import com.example.eSportsPM.services.UserProfileServices.GetProfileService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Service
@AllArgsConstructor
@RequestMapping("/api/profiles")
@RestController
public class UserProfileController {
    private final GetProfileService getProfileService;

    @GetMapping("/{id}")
    public ResponseEntity<UserProfileDTO> getProfile(@PathVariable UUID id){
        return getProfileService.getProfile(id);
    }
}
