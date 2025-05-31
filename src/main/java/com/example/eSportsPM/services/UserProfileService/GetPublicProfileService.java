package com.example.eSportsPM.services.UserProfileService;

import com.example.eSportsPM.DTOs.UserProfile.UserPublicProfileDTO;

import com.example.eSportsPM.models.UserPublicInfo;
import com.example.eSportsPM.repositories.UserPublicInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class GetPublicProfileService {
    private final UserPublicInfoRepository publicInfoRepository;

    public ResponseEntity<UserPublicProfileDTO> getPublicProfileById(UUID id){
        Optional<UserPublicInfo> info = publicInfoRepository.findById(id);
        if (info.isEmpty()){
        }
        return ResponseEntity.ok(new UserPublicProfileDTO(info.get()));
    }


    public ResponseEntity<UserPublicProfileDTO> getPublicProfileByUsername(String username){
        Optional<UserPublicInfo> info = publicInfoRepository.findByPublicProfilePath(username);
        if (info.isEmpty()){
        }
        return ResponseEntity.ok(new UserPublicProfileDTO(info.get()));
    }
}
