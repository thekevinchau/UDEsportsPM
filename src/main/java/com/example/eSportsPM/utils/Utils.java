package com.example.eSportsPM.utils;

import com.example.eSportsPM.models.User;
import com.example.eSportsPM.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class Utils {

    public static User getUser(UserRepository userRepository){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> userOptional = userRepository.findByEmail(auth.getName());
        if (userOptional.isEmpty()){
            throw new RuntimeException("User not found");
        }
        return userOptional.get();
    }
}
