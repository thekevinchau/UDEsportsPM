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

    public void verifyOrgId (UUID orgId) throws AccessDeniedException {
        User user = Utils.getUser(userRepository);
        if (user.getOrganization() == null){
            throw new AccessDeniedException("You do not have access to this resource.");
        }
        else if (!user.getOrganization().getId().equals(orgId)){
            throw new AccessDeniedException("You do not have access to this resource.");
        }
    }

}
