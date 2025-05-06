package com.example.eSportsPM.services.UserServices.UserProfileService;

import com.example.eSportsPM.models.User;
import com.example.eSportsPM.models.UserPrivateInfo;
import com.example.eSportsPM.models.UserPublicInfo;
import com.example.eSportsPM.repositories.UserPrivateInfoRepository;
import com.example.eSportsPM.repositories.UserPublicInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CreateProfileService {
    private final UserPrivateInfoRepository privateInfoRepository;
    private final UserPublicInfoRepository publicInfoRepository;

    public void createPrivateProfile (User user, String fullName){
        UserPrivateInfo privateInfo = new UserPrivateInfo();
        privateInfo.setUser(user);
        privateInfo.setFullName(fullName);
        privateInfoRepository.save(privateInfo);
    }

    public void createPublicProfile(User user, String gamerTag){
        UserPublicInfo publicInfo = new UserPublicInfo();
        publicInfo.setUser(user);
        publicInfo.setGamerTag(gamerTag);
        publicInfoRepository.save(publicInfo);
    }
}
