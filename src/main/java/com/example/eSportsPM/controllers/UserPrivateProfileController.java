package com.example.eSportsPM.controllers;

import com.example.eSportsPM.DTOs.UserProfile.PrivateProfileEdit;
import com.example.eSportsPM.DTOs.UserProfile.UserPrivateProfileDTO;
import com.example.eSportsPM.exceptions.UserNotFound;
import com.example.eSportsPM.services.UserProfileService.EditPrivateProfileService;
import com.example.eSportsPM.services.UserProfileService.GetPrivateProfileService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.UUID;

@RestController
@RequestMapping("/api/profiles/private")
@AllArgsConstructor
public class UserPrivateProfileController {
    private final GetPrivateProfileService getProfileService;
    private final EditPrivateProfileService editProfileService;


    @GetMapping("/{id}")
    public ResponseEntity<UserPrivateProfileDTO> getProfileById (@PathVariable UUID id) throws AccessDeniedException {
        return getProfileService.getProfileById(id);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<UserPrivateProfileDTO> editPrivateProfile (@PathVariable UUID id, @Valid @RequestBody PrivateProfileEdit edits) throws BadRequestException {
        System.out.println(edits.getFullName());
        return editProfileService.editPrivateProfile(id, edits);
    }
}
