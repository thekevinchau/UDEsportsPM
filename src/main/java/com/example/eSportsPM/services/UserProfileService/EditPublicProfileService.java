package com.example.eSportsPM.services.UserProfileService;

import com.example.eSportsPM.DTOs.UserProfile.PublicProfileEdit;
import com.example.eSportsPM.DTOs.UserProfile.UserPublicProfileDTO;
import com.example.eSportsPM.models.UserPublicInfo;
import com.example.eSportsPM.repositories.UserPublicInfoRepository;
import com.example.eSportsPM.repositories.UserRepository;
import com.example.eSportsPM.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class EditPublicProfileService {
    private final UserPublicInfoRepository publicInfoRepository;
    private final UserRepository userRepository;


    private UserPublicInfo getCurrentPublicProfile(){
        UUID currentUserId = Utils.getUser(userRepository).getId();
        Optional<UserPublicInfo> publicInfoOptional = publicInfoRepository.findById(currentUserId);
        if (publicInfoOptional.isEmpty()){
        }
        return publicInfoOptional.get();
    }

    public ResponseEntity<UserPublicProfileDTO> editPublicInfo(PublicProfileEdit edits, UUID id) throws AccessDeniedException {
        UserPublicInfo currentProfile = getCurrentPublicProfile();
        System.out.println("Current user ID: " + currentProfile.getUser().getId());
        System.out.println("Passed in ID: " + id);
        //If the current's profile userID doesn't equal the ID passed in, then this isn't their resource to edit.
        if (!currentProfile.getUser().getId().equals(id)){
            throw new AccessDeniedException("You are not allowed to access this resource");
        }
        //handle edit logic here by grabbing information from client
        if (edits.getGamerTag() != null){
            currentProfile.setGamerTag(edits.getGamerTag());
        }
        if (edits.getBio() != null){
            currentProfile.setBio(edits.getBio());
        }
        if (edits.getUserClass() != null){
            currentProfile.setUserClass(edits.getUserClass());
        }
        if (edits.getAvatarURL() != null){
            currentProfile.setAvatarPath(edits.getAvatarURL());
        }
        if (edits.getBannerURL() != null){
            currentProfile.setBannerPath(edits.getBannerURL());
        }
        if (edits.getExpectedGrad() != null){
            currentProfile.setExpectedGrad(edits.getExpectedGrad());
        }

        return ResponseEntity.ok(new UserPublicProfileDTO(publicInfoRepository.save(currentProfile)));

    }

}
