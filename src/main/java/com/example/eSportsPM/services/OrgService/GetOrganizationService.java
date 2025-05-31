package com.example.eSportsPM.services.OrgService;

import com.example.eSportsPM.DTOs.Organization.OrganizationDTO;
import com.example.eSportsPM.exceptions.NotFoundException;
import com.example.eSportsPM.models.Organization;
import com.example.eSportsPM.repositories.OrgRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GetOrganizationService {

    private final OrgRepository orgRepository;

    public ResponseEntity<OrganizationDTO> getOrganizationByUrl(String urlPath){
        Optional<Organization> org = orgRepository.findByUrlPath(urlPath);
        if (org.isEmpty()){
            throw new NotFoundException("Organization was not found");
        }
        return ResponseEntity.ok(new OrganizationDTO(org.get()));
    }

    public ResponseEntity<OrganizationDTO> getOrganizationByName(String name){
        Optional<Organization> org = orgRepository.findByName(name);
        if (org.isEmpty()){
            throw new NotFoundException("Organization was not found.");
        }
        return ResponseEntity.ok(new OrganizationDTO(org.get()));
    }
}
