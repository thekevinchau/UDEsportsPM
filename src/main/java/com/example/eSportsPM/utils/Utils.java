package com.example.eSportsPM.utils;

import com.example.eSportsPM.models.User;
import com.example.eSportsPM.repositories.UserRepository;

import java.util.Optional;

public class Utils {

    public static User getUser(String email, UserRepository userRepository){
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()){
            throw new RuntimeException("User not found");
        }
        return userOptional.get();
    }
}
