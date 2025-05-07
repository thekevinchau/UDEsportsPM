package com.example.eSportsPM.services.UserProfileService;

import com.example.eSportsPM.exceptions.UserNotFound;
import com.example.eSportsPM.models.UserPrivateInfo;
import com.example.eSportsPM.repositories.UserPrivateInfoRepository;
import com.example.eSportsPM.repositories.UserRepository;
import com.example.eSportsPM.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
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
            throw new UserNotFound("User profile does not exist");
        }
        return privateInfoOptional.get();
    }

    //User sends their JWT in and this is how they edit only their profile.
    public void editPrivateInfo () throws AccessDeniedException {
        UserPrivateInfo currentProfile = getCurrentPrivateProfile();
        if (currentProfile.getUser().getId() != Utils.getUser(userRepository).getId()){
            throw new AccessDeniedException("You are not allowed to access this resource");
        }


    }
}
