package com.example.eSportsPM.services.UserServices;

import com.example.eSportsPM.models.User;
import com.example.eSportsPM.models.UserProfile;
import com.example.eSportsPM.repositories.UserProfileRepository;
import com.example.eSportsPM.repositories.UserRepository;
import com.example.eSportsPM.security.AuthorizationService;
import com.example.eSportsPM.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class EditUserService {
    private final UserProfileRepository userProfileRepository;
    private final UserRepository userRepository;
    private final AuthorizationService authService;

}
