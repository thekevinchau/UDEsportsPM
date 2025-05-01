package com.example.eSportsPM.services.OrganizationServices;

import com.example.eSportsPM.DTOs.OrganizationDTOs.OrgRegRequestDTO;
import com.example.eSportsPM.DTOs.OrganizationDTOs.OrganizationRegistrationDTO;
import com.example.eSportsPM.models.OrgRegistration;
import com.example.eSportsPM.models.User;
import com.example.eSportsPM.repositories.OrganizationRegistrationRepository;
import com.example.eSportsPM.repositories.OrganizationRepository;
import com.example.eSportsPM.repositories.UserRepository;
import com.example.eSportsPM.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class CreateOrganizationService {

    private final OrganizationRepository organizationRepository;
    private final OrganizationRegistrationRepository organizationRegistrationRepository;
    private final UserRepository userRepository;

    /*
    Register an organization
    - Client submits a request to register for an organization (College)
        - This gets sent to a table that stores the status of organizations and awaiting approval.
        - Client submits org name, billing email/org email. As well as the user making the request (most likely
        the org owner.)
        - If approved, create an organization with the provided details and allow client to handle the rest of their
            information for the organization.
     */

    public ResponseEntity<OrganizationRegistrationDTO> requestOrganizationCreation(OrgRegRequestDTO request){
        OrgRegistration registration = new OrgRegistration();
        registration.setOrgName(request.getOrgName());
        registration.setDescription(request.getDescription());
        registration.setOrgEmail(request.getOrgEmail());
        registration.setBillingEmail(request.getBillingEmail());
        registration.setEstNumTeams(request.getEstNumTeams());
        registration.setStatus("Pending");
        registration.setCreatedAt(OffsetDateTime.now());
        registration.setRequestedBy(Utils.getUser(userRepository));
        registration.setUpdatedBy(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(new OrganizationRegistrationDTO(organizationRegistrationRepository.save(registration)));
    }

}
