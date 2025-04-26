package com.example.eSportsPM.controllers;

import com.example.eSportsPM.DTOs.OrganizationDTOs.OrgRegRequestDTO;
import com.example.eSportsPM.DTOs.OrganizationDTOs.OrganizationDTO;
import com.example.eSportsPM.DTOs.OrganizationDTOs.OrganizationRegistrationDTO;
import com.example.eSportsPM.services.CreateOrganizationService;
import com.example.eSportsPM.services.GetOrganizationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organizations")
public class OrganizationController {

    private final GetOrganizationService getOrganizationService;
    public final CreateOrganizationService createOrganizationService;

    public OrganizationController(GetOrganizationService getOrganizationService, CreateOrganizationService createOrganizationService) {
        this.getOrganizationService = getOrganizationService;
        this.createOrganizationService = createOrganizationService;
    }


    @GetMapping()
    public ResponseEntity<List<OrganizationDTO>> GetAllOrganizations(){
        return getOrganizationService.getAllOrganizations();
    }

    @PostMapping("/register")
    public ResponseEntity<OrganizationRegistrationDTO> requestOrganization(@RequestBody OrgRegRequestDTO request){
        return createOrganizationService.requestOrganizationCreation(request);
    }

}
