package com.example.eSportsPM.controllers;


import com.example.eSportsPM.DTOs.OrgCreation;
import com.example.eSportsPM.DTOs.OrganizationDTO;
import com.example.eSportsPM.services.OrgService.CreateOrganizationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/organization")
@AllArgsConstructor
public class OrgController {

    private final CreateOrganizationService createOrgService;

    @PostMapping()
    public ResponseEntity<OrganizationDTO> createOrganization(@Valid @RequestBody OrgCreation orgDetails){
        return createOrgService.createOrganization(orgDetails);
    }
}
