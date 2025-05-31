package com.example.eSportsPM.services.UserProfileService;

import com.example.eSportsPM.DTOs.UserProfile.PrivateProfileEdit;
import com.example.eSportsPM.DTOs.UserProfile.UserPrivateProfileDTO;
import com.example.eSportsPM.models.UserPrivateInfo;
import com.example.eSportsPM.repositories.UserPrivateInfoRepository;
import com.example.eSportsPM.repositories.UserRepository;
import com.example.eSportsPM.utils.Utils;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class EditPrivateProfileService {

    private final UserPrivateInfoRepository privateInfoRepo;
    private final UserRepository userRepository;


    private UserPrivateInfo getCurrentPrivateProfile(){
        UUID currentUserId = Utils.getUser(userRepository).getId();
        Optional<UserPrivateInfo> privateInfoOptional = privateInfoRepo.findById(currentUserId);
        if (privateInfoOptional.isEmpty()){
        }
        return privateInfoOptional.get();
    }

    private UserPrivateInfo handleEdits (UserPrivateInfo currentProfile, PrivateProfileEdit edits) throws BadRequestException {
        currentProfile.setFullName(edits.getFullName()); //these two are validated to be not null
        currentProfile.setAvailable(edits.isAvailable()); //these two are validated to be not null
        currentProfile.setEmergencyContactName(edits.getEmergencyContactName() == null ? "" : edits.getEmergencyContactName());
        currentProfile.setEmergencyContactRelation(edits.getEmergencyContactRelation() == null ? "" : edits.getEmergencyContactRelation());
        currentProfile.setEmergencyContactPhone(edits.getEmergencyContactPhone() == null ? "" : edits.getEmergencyContactPhone());

        if (currentProfile.getUnavailableEnd().isBefore(edits.getUnavailableStart())){
            throw new BadRequestException("Unavailability end date cannot be before start date.");
        }
        else if (currentProfile.getUnavailableStart().isAfter(edits.getUnavailableEnd())){
            throw new BadRequestException("Unavailability start date cannot be after end date.");
        }
        else{
            currentProfile.setUnavailableStart(edits.getUnavailableStart());
            currentProfile.setUnavailableEnd(edits.getUnavailableEnd());
        }

        return currentProfile;
    }

    public ResponseEntity<UserPrivateProfileDTO> editPrivateProfile (UUID id, PrivateProfileEdit edits) throws BadRequestException {
        UserPrivateInfo currentProfile = getCurrentPrivateProfile();
        if (!currentProfile.getUserId().equals(id)){ //if the user making the request's ID does not match the ID of the profile, then deny the request
            throw new AccessDeniedException("You are not allowed to access this resource");
        }
        UserPrivateInfo updatedInfo = handleEdits(currentProfile, edits);
        return ResponseEntity.ok(new UserPrivateProfileDTO(privateInfoRepo.save(updatedInfo)));




    }
}
