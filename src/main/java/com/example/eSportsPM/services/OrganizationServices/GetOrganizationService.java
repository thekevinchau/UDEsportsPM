package com.example.eSportsPM.services.OrganizationServices;

import com.example.eSportsPM.DTOs.OrganizationDTOs.OrganizationDTO;
import com.example.eSportsPM.models.Organization;
import com.example.eSportsPM.repositories.OrganizationRepository;
import com.example.eSportsPM.repositories.UserRepository;
import com.example.eSportsPM.security.AuthorizationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class GetOrganizationService {

    private final OrganizationRepository organizationRepository;
    private final UserRepository userRepository;
    private final AuthorizationService authorizationService;

    public ResponseEntity<List<OrganizationDTO>> getAllOrganizations(){
        List<OrganizationDTO> organizations = organizationRepository.findAll().stream().map(OrganizationDTO::new).toList();
        return ResponseEntity.ok(organizations);
    }

    public ResponseEntity<OrganizationDTO> getOrganizationById (UUID orgId) throws Exception{
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<Organization> organizationOptional = organizationRepository.findById(orgId);
        authorizationService.verifyOrgId(orgId);
        if (organizationOptional.isEmpty()){
            //throw not found error
        }
        return ResponseEntity.ok(new OrganizationDTO(organizationOptional.get()));
    }
}
