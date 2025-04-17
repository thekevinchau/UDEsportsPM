package com.example.eSportsPM.services;

import com.example.eSportsPM.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.eSportsPM.repositories.UserRepository;

import java.util.List;

@Service
public class GetUserService {

    private final UserRepository userRepository;

    public GetUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userRepository.findAll());
    }


}
