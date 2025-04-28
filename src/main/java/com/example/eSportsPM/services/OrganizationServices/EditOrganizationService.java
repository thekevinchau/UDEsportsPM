package com.example.eSportsPM.services.OrganizationServices;

import com.example.eSportsPM.DTOs.OrganizationDTOs.OrganizationRegistrationDTO;
import com.example.eSportsPM.models.OrgRegistration;
import com.example.eSportsPM.repositories.OrganizationRegistrationRepository;
import com.example.eSportsPM.repositories.OrganizationRepository;
import com.example.eSportsPM.repositories.UserRepository;
import com.example.eSportsPM.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class EditOrganizationService {

    private final OrganizationRepository organizationRepository;
    private final OrganizationRegistrationRepository organizationRegistrationRepository;
    private final UserRepository userRepository;

    public ResponseEntity<OrganizationRegistrationDTO> approveOrgRegRequest (UUID registrationId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<OrgRegistration> registrationOptional = organizationRegistrationRepository.findById(registrationId);
        if (registrationOptional.isEmpty()){
            //
        }
        OrgRegistration registration = registrationOptional.get();
        registration.setStatus("Approved");
        registration.setUpdatedBy(Utils.getUser(auth.getName(), userRepository));
        return ResponseEntity.ok(new OrganizationRegistrationDTO(organizationRegistrationRepository.save(registration)));
    }
}
