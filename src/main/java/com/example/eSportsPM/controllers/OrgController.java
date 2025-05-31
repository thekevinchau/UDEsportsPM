package com.example.eSportsPM.controllers;


import com.example.eSportsPM.DTOs.Organization.OrgCreation;
import com.example.eSportsPM.DTOs.Organization.OrganizationDTO;
import com.example.eSportsPM.services.OrgService.CreateOrganizationService;
import com.example.eSportsPM.services.OrgService.GetOrganizationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/organizations")
@AllArgsConstructor
public class OrgController {

    private final CreateOrganizationService createOrgService;
    private final GetOrganizationService getOrgService;

    @PostMapping()
    public ResponseEntity<OrganizationDTO> createOrganization(@Valid @RequestBody OrgCreation orgDetails){
        return createOrgService.createOrganization(orgDetails);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizationDTO> getOrganizationById(@PathVariable String id){
        return getOrgService.getOrganizationByUrl(id);
    }
}
