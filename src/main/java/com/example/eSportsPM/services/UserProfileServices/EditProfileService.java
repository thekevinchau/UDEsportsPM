package com.example.eSportsPM.services.UserProfileServices;

import com.example.eSportsPM.DTOs.UserDTOs.UserProfileDTO;
import com.example.eSportsPM.DTOs.UserDTOs.UserProfileEdit;
import com.example.eSportsPM.exceptions.UserNotFound;
import com.example.eSportsPM.models.UserProfile;
import com.example.eSportsPM.repositories.UserProfileRepository;
import com.example.eSportsPM.repositories.UserRepository;
import com.example.eSportsPM.security.AuthorizationService;
import com.example.eSportsPM.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class EditProfileService {
    private final UserProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final AuthorizationService authService;

    private UserProfile findProfile(UUID id) {
        Optional<UserProfile> profileOptional = profileRepository.findById(id);
        if (profileOptional.isEmpty()) {
            throw new UserNotFound("User was not found");
        }
        return profileOptional.get();
    }

    private void handleTextChanges(UserProfile profile, String fullName, String bio, String pronouns){
        //text validate and then use setters
        profile.setFullName(fullName);
        profile.setBiography(bio);
        profile.setPronouns(pronouns);
    }

    private void handleOutOfOffice(UserProfile profile, boolean isOutOfOffice, OffsetDateTime startTime, OffsetDateTime endTime){
        if (!isOutOfOffice){
            profile.setOutOfOffice(false);
            profile.setOutOfOfficeStart(null);
            profile.setOutOfOfficeEnd(null);
        }
        else {
            profile.setOutOfOffice(true);
            profile.setOutOfOfficeStart(startTime);
            profile.setOutOfOfficeEnd(endTime);
        }
    }

    public ResponseEntity<UserProfileDTO> editProfile (UserProfileEdit updatedProfile){
        UUID currentUserId = Utils.getUser(userRepository).getId();
        UserProfile profile = findProfile(currentUserId);
        handleTextChanges(profile, updatedProfile.getUsername(), updatedProfile.getBiography(), updatedProfile.getPronouns());
        handleOutOfOffice(profile, updatedProfile.getOutOfOffice(), updatedProfile.getOutOfOfficeStart(), updatedProfile.getOutOfOfficeEnd());
        return ResponseEntity.ok(new UserProfileDTO(profileRepository.save(profile)));
    }
}


