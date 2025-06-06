package com.example.eSportsPM.controllers;

import com.example.eSportsPM.DTOs.Organization.OrgProfileDTO;
import com.example.eSportsPM.services.OrgProfileService.GetOrgProfileService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/org/profiles")
@AllArgsConstructor
public class OrgProfileController {
    private final GetOrgProfileService GetOrgProfileService;

    @GetMapping("/me")
    public ResponseEntity<List<OrgProfileDTO>> getOrgProfilesByUser(){
        return GetOrgProfileService.getOrgProfilesByUser();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrgProfileDTO> getOrgProfileById(@PathVariable UUID id){
        return GetOrgProfileService.getOrgProfileById(id);
    }

    @GetMapping("/primary")
    public ResponseEntity<OrgProfileDTO> getPrimaryOrgProfile(){
        return GetOrgProfileService.getCurrentOrgProfile();
    }
}
