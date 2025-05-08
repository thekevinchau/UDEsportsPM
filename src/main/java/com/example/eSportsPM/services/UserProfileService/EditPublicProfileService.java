package com.example.eSportsPM.services.UserProfileService;

import com.example.eSportsPM.exceptions.UserNotFound;
import com.example.eSportsPM.models.UserPrivateInfo;
import com.example.eSportsPM.models.UserPublicInfo;
import com.example.eSportsPM.repositories.UserPrivateInfoRepository;
import com.example.eSportsPM.repositories.UserPublicInfoRepository;
import com.example.eSportsPM.repositories.UserRepository;
import com.example.eSportsPM.utils.Utils;
import lombok.AllArgsConstructor;
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
            throw new UserNotFound(("User profile does not exist"));
        }
        return publicInfoOptional.get();
    }

    public void editPublicInfo() throws AccessDeniedException {
        UserPublicInfo currentProfile = getCurrentPublicProfile();
        if (currentProfile.getUser().getId() != Utils.getUser(userRepository).getId()){
            throw new AccessDeniedException("You are not allowed to access this resource");
        }

    }

}
