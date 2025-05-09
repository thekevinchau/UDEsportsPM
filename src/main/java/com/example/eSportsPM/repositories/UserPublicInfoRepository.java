package com.example.eSportsPM.repositories;

import com.example.eSportsPM.models.UserPublicInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserPublicInfoRepository extends JpaRepository<UserPublicInfo, UUID> {
    Optional<UserPublicInfo> findByPublicProfilePath(String pathName);
}
