package com.example.eSportsPM.services;

import com.example.eSportsPM.DTOs.OrganizationDTOs.OrganizationDTO;
import com.example.eSportsPM.repositories.OrganizationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetOrganizationService {

    private final OrganizationRepository organizationRepository;


    public GetOrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    public ResponseEntity<List<OrganizationDTO>> getAllOrganizations(){
        List<OrganizationDTO> organizations = organizationRepository.findAll().stream().map(OrganizationDTO::new).toList();
        return ResponseEntity.ok(organizations);
    }
}
