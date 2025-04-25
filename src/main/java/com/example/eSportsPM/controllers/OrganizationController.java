package com.example.eSportsPM.controllers;

import com.example.eSportsPM.DTOs.OrganizationDTO;
import com.example.eSportsPM.models.Organization;
import com.example.eSportsPM.services.GetOrganizationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/organizations")
public class OrganizationController {

    private final GetOrganizationService getOrganizationService;

    public OrganizationController(GetOrganizationService getOrganizationService) {
        this.getOrganizationService = getOrganizationService;
    }


    @GetMapping()
    public ResponseEntity<List<OrganizationDTO>> GetAllOrganizations(){
        return getOrganizationService.getAllOrganizations();
    }

}
