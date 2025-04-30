package com.example.eSportsPM.services.UserServices;

import com.example.eSportsPM.models.UserProfile;
import com.example.eSportsPM.repositories.UserProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EditUserService {
    private final UserProfileRepository userProfileRepository;

    public ResponseEntity<UserProfile> editProfile (UserProfile userProfile){
        return null;
    }

}
