package com.example.eSportsPM.services.UserProfileServices;

import com.example.eSportsPM.DTOs.UserDTOs.UserProfileDTO;
import com.example.eSportsPM.exceptions.UserNotFound;
import com.example.eSportsPM.models.UserProfile;
import com.example.eSportsPM.repositories.UserProfileRepository;
import com.example.eSportsPM.security.AuthorizationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class EditProfileService {
    private final UserProfileRepository profileRepository;
    private final AuthorizationService authService;

    private UserProfile findProfile(UUID id) {
        Optional<UserProfile> profileOptional = profileRepository.findById(id);
        if (profileOptional.isEmpty()) {
            throw new UserNotFound("User was not found");
        }
        return profileOptional.get();
    }

    /*
    public void updateProfile (UUID id, UserProfile profile) throws AccessDeniedException {
        UserProfile userProfile = findProfile(id);
        System.out.println(userProfile);
        /*
        authService.verifyUserId(userProfile.getUser().getId());
        userProfile.setId(profile.getId());
        userProfile.setBiography(profile.getBiography());
        userProfile.setPronouns(profile.getPronouns());

        if (profile.getOutOfOffice()){
            userProfile.setOutOfOffice(true);
            userProfile.setOutOfOfficeStart(profile.getOutOfOfficeStart());
            userProfile.setOutOfOfficeEnd(profile.getOutOfOfficeEnd());
        }
        else {
            userProfile.setOutOfOffice(false);
            userProfile.setOutOfOfficeStart(null);
            userProfile.setOutOfOfficeEnd(null);
        }
        UserProfile savedProfile = profileRepository.save(userProfile);

         */

}

