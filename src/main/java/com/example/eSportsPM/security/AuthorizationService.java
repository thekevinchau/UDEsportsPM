package com.example.eSportsPM.security;

import com.example.eSportsPM.models.User;
import com.example.eSportsPM.repositories.UserRepository;
import com.example.eSportsPM.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthorizationService {
    private final UserRepository userRepository;


    //This function calls Utils.getUser which retrieves the currently signed in user from the database (authentication context)
    //Now it compares the userId passed in, to the userId of the currently signed in user. If not the same, then they're not allowed to access another person's resource

    public void verifyUserId (UUID userId) throws AccessDeniedException {
        User user = Utils.getUser(userRepository);
        if (userId != user.getId()){
            throw new AccessDeniedException("You are not allowed to access this resource.");
        }
    }

}
