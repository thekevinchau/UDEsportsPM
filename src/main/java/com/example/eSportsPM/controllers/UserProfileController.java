package com.example.eSportsPM.controllers;

import com.example.eSportsPM.DTOs.UserDTOs.UserProfileDTO;
import com.example.eSportsPM.DTOs.UserDTOs.UserProfileEdit;
import com.example.eSportsPM.models.UserProfile;
import com.example.eSportsPM.services.UserProfileServices.EditProfileService;
import com.example.eSportsPM.services.UserProfileServices.GetProfileService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@Service
@AllArgsConstructor
@RequestMapping("/api/profiles")
@RestController
public class UserProfileController {
    private final GetProfileService getProfileService;
    private final EditProfileService editProfileService;

    @GetMapping("/{id}")
    public ResponseEntity<UserProfileDTO> getProfile(@PathVariable UUID id){
        return getProfileService.getProfile(id);
    }

    @PutMapping()
    public ResponseEntity<UserProfileDTO> editProfile(@Valid @RequestBody UserProfileEdit profile){
        return editProfileService.editProfile(profile);
    }

}