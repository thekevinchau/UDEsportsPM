package com.example.eSportsPM.services.UserProfileServices;

import com.example.eSportsPM.DTOs.UserDTOs.UserProfileDTO;
import com.example.eSportsPM.exceptions.UserNotFound;
import com.example.eSportsPM.models.UserProfile;
import com.example.eSportsPM.repositories.UserProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class GetProfileService {
    private final UserProfileRepository profileRepository;

    public ResponseEntity<UserProfileDTO> getProfile(UUID userId){
        Optional<UserProfile> profile = profileRepository.findById(userId);
        if (profile.isEmpty()){
            throw new UserNotFound("User profile does not exist!");
        }
        return ResponseEntity.ok(new UserProfileDTO(profile.get()));
    }

}
