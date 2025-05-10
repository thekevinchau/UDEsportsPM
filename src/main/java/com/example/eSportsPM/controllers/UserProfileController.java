package com.example.eSportsPM.controllers;


import com.example.eSportsPM.DTOs.UserProfile.PublicProfileEdit;
import com.example.eSportsPM.DTOs.UserProfile.UserPublicProfileDTO;
import com.example.eSportsPM.models.User;
import com.example.eSportsPM.services.UserProfileService.EditPublicProfileService;
import com.example.eSportsPM.services.UserProfileService.GetPublicProfileService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.UUID;

@RestController
@RequestMapping("/api/profiles")
@AllArgsConstructor
public class UserProfileController {

    private final EditPublicProfileService editPublicProfileService;
    private final GetPublicProfileService getProfileService;

    @GetMapping("/public/{id}")
    public ResponseEntity<UserPublicProfileDTO> getPublicProfileById(@PathVariable UUID id){
        return getProfileService.getPublicProfileById(id);
    }


    @GetMapping("/public")
    public ResponseEntity<UserPublicProfileDTO> getPublicProfileByUsername(@RequestParam String username){
        return getProfileService.getPublicProfileByUsername(username);
    }


    @PatchMapping("/public/{id}")
    public ResponseEntity<UserPublicProfileDTO> editPublicProfile(@PathVariable UUID id, @RequestBody PublicProfileEdit edits) throws AccessDeniedException {
        return editPublicProfileService.editPublicInfo(edits, id);
    }



}
