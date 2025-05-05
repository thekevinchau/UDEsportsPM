package com.example.eSportsPM.services.UserProfileServices;

import com.example.eSportsPM.models.UserProfile;
import com.example.eSportsPM.repositories.UserProfileRepository;
import com.example.eSportsPM.repositories.UserRepository;
import com.example.eSportsPM.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CreateProfileService {
    private final UserProfileRepository profileRepository;
    private final UserRepository userRepository;


    /*
    This will be called from the createUser function/endpoint.
    CreateUser will create the user first, then grab its ID and passes it into this function to allow the profile to point to the newly created user.
     */
    public void createProfile(UUID userId, String fullName){
        UserProfile newProfile = new UserProfile();
        newProfile.setUser(Utils.getUserById(userId, userRepository)); //will throw an exception if the User was not found.
        newProfile.setFullName(fullName);
        newProfile.setPronouns("");
        newProfile.setBiography("");
        newProfile.setOutOfOffice(false);
        newProfile.setOutOfOfficeStart(null);
        newProfile.setOutOfOfficeEnd(null);
        profileRepository.save(newProfile);
    }
}
