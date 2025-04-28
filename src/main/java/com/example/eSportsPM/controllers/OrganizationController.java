package com.example.eSportsPM.controllers;

import com.example.eSportsPM.DTOs.OrganizationDTOs.OrgRegRequestDTO;
import com.example.eSportsPM.DTOs.OrganizationDTOs.OrganizationDTO;
import com.example.eSportsPM.DTOs.OrganizationDTOs.OrganizationRegistrationDTO;
import com.example.eSportsPM.services.OrganizationServices.CreateOrganizationService;
import com.example.eSportsPM.services.OrganizationServices.EditOrganizationService;
import com.example.eSportsPM.services.OrganizationServices.GetOrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/organizations")
@AllArgsConstructor
public class OrganizationController {

    private final GetOrganizationService getOrganizationService;
    private final CreateOrganizationService createOrganizationService;
    private final EditOrganizationService editOrganizationService;


    @GetMapping()
    public ResponseEntity<List<OrganizationDTO>> GetAllOrganizations(){
        return getOrganizationService.getAllOrganizations();
    }

    @PostMapping("/register")
    public ResponseEntity<OrganizationRegistrationDTO> requestOrganization(@RequestBody OrgRegRequestDTO request){
        return createOrganizationService.requestOrganizationCreation(request);
    }

    @PutMapping("/requests/{id}")
    public ResponseEntity<OrganizationRegistrationDTO> approveRequest (@PathVariable UUID id){
        return editOrganizationService.approveOrgRegRequest(id);
    }

}
