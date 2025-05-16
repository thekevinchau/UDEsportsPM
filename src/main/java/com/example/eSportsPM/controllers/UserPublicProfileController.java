package com.example.eSportsPM.controllers;


import com.example.eSportsPM.DTOs.UserProfile.PublicProfileEdit;
import com.example.eSportsPM.DTOs.UserProfile.UserPublicProfileDTO;
import com.example.eSportsPM.services.AwsServices.S3Service;
import com.example.eSportsPM.services.UserProfileService.EditPublicProfileService;
import com.example.eSportsPM.services.UserProfileService.GetPublicProfileService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.UUID;

@RestController
@RequestMapping("/api/profiles/public")
@AllArgsConstructor
public class UserPublicProfileController {

    private final EditPublicProfileService editPublicProfileService;
    private final GetPublicProfileService getProfileService;
    private final S3Service S3Service;

    @GetMapping("/{id}")
    public ResponseEntity<UserPublicProfileDTO> getPublicProfileById(@PathVariable UUID id){
        return getProfileService.getPublicProfileById(id);
    }


    @GetMapping()
    public ResponseEntity<UserPublicProfileDTO> getPublicProfileByUsername(@RequestParam String username){
        return getProfileService.getPublicProfileByUsername(username);
    }

    @GetMapping("/getProfileImage")
    public ResponseEntity<String> getImagePresignedURL(@RequestParam String path){
        return S3Service.generatePreSignedUrlDownload(path);
    }

    //client sends a photo to our backend, then the backend gets a pre-signed URL from s3 and then the server is able to return that URL back to
    //the client so that they can make a PUT request to that URL and upload the image.
    @GetMapping("/uploadImage")
    public ResponseEntity<String> uploadImagePresignedURL(@RequestParam String path){
        return S3Service.generatePreSignedUrlUpload(path);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<UserPublicProfileDTO> editPublicProfile(@PathVariable UUID id, @RequestBody PublicProfileEdit edits) throws AccessDeniedException {
        return editPublicProfileService.editPublicInfo(edits, id);
    }



}
