package com.example.eSportsPM.services.OrgService;

import com.example.eSportsPM.DTOs.Organization.OrgCreation;
import com.example.eSportsPM.DTOs.Organization.OrganizationDTO;
import com.example.eSportsPM.exceptions.OrgExistsException;
import com.example.eSportsPM.models.Organization;
import com.example.eSportsPM.repositories.OrgRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class CreateOrganizationService {

    private final OrgRepository orgRepository;

    public static String generateUrlHash(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));

            // Convert to hex and trim to 10 characters for URL shortness
            StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < 5; i++) { // 5 bytes = 10 hex chars
                hexString.append(String.format("%02x", hashBytes[i]));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found", e);
        }
    }

    public ResponseEntity<OrganizationDTO> createOrganization(OrgCreation orgDetails){
        if (orgRepository.findByName(orgDetails.getName()).isPresent()){
            throw new OrgExistsException("Organization already exists!");
        }
        Organization newOrganization = new Organization();
        newOrganization.setName(orgDetails.getName());
        newOrganization.setDescription(orgDetails.getDescription());
        newOrganization.setType(orgDetails.getType());
        newOrganization.setAvatarPath(orgDetails.getAvatarPath());
        newOrganization.setBannerPath(orgDetails.getBannerPath());
        newOrganization.setUrlPath(generateUrlHash(orgDetails.getName()));
        newOrganization.setCreatedAt(OffsetDateTime.now());
        newOrganization.setRegion(orgDetails.getRegion());
        Organization savedOrg = orgRepository.save(newOrganization);
        return ResponseEntity.status(HttpStatus.CREATED).body(new OrganizationDTO(savedOrg));

    }
}
