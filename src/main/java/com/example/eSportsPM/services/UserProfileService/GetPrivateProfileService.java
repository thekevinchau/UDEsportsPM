package com.example.eSportsPM.services.UserProfileService;

import com.example.eSportsPM.DTOs.UserProfile.UserPrivateProfileDTO;
import com.example.eSportsPM.exceptions.UserNotFound;
import com.example.eSportsPM.models.User;
import com.example.eSportsPM.models.UserPrivateInfo;
import com.example.eSportsPM.repositories.UserPrivateInfoRepository;
import com.example.eSportsPM.repositories.UserRepository;
import com.example.eSportsPM.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class GetPrivateProfileService {
    private final UserPrivateInfoRepository privateInfoRepository;
    private final UserRepository userRepository;

    public ResponseEntity<UserPrivateProfileDTO> getProfileById(UUID id) throws AccessDeniedException {
        User user = Utils.getUser(userRepository);
        if (!user.getId().equals(id) && !(user.getRole().equals("ROLE_ADMIN"))){
            System.out.println("Current User's Role" + user.getRole());
            System.out.println("UserID: " + user.getId());
            System.out.println("Passed in ID:" + id);
            throw new AccessDeniedException("You do not have access to this resource.");
        }
        Optional<UserPrivateInfo> profileOptional = privateInfoRepository.findById(id);
        if (profileOptional.isEmpty()){
            throw new UserNotFound("User was not found.");
        }
        return ResponseEntity.ok(new UserPrivateProfileDTO(profileOptional.get()));
    }
}
