package com.example.eSportsPM.services.OrgProfileService;

import com.example.eSportsPM.DTOs.Organization.OrgProfileDTO;
import com.example.eSportsPM.exceptions.NotFoundException;
import com.example.eSportsPM.models.OrgProfile;
import com.example.eSportsPM.repositories.OrgProfileRepository;
import com.example.eSportsPM.repositories.UserRepository;
import com.example.eSportsPM.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class GetOrgProfileService {
    private final OrgProfileRepository orgProfileRepo;
    private final UserRepository userRepository;

    //Helpful for listing out all organization profiles that a user is a part of.
    public ResponseEntity<List<OrgProfileDTO>> getOrgProfilesByUser (){
        UUID userId = Utils.getUser(userRepository).getId();
        List<OrgProfile> profiles = orgProfileRepo.findOrgProfilesByUser(userId);
        List<OrgProfileDTO> profileDTOs = profiles.stream().map(OrgProfileDTO::new).toList();
        return ResponseEntity.ok(profileDTOs);
    }

    //Helpful for when client sends request to get the current information about their profile in the organization.
    public ResponseEntity<OrgProfileDTO> getOrgProfileById (UUID id){
        UUID currentUserId = Utils.getUser(userRepository).getId();
        Optional<OrgProfile> profile = orgProfileRepo.findById(id);
        if (profile.isEmpty()){
            throw new NotFoundException("Profile was not found.");
        }
        if (!currentUserId.equals(profile.get().getUserId())){
            throw new RuntimeException("You are not allowed to access this resource.");
        }
        return ResponseEntity.ok(new OrgProfileDTO(profile.get()));
    }
}
