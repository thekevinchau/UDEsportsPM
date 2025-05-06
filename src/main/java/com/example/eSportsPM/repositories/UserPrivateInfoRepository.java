package com.example.eSportsPM.repositories;

import com.example.eSportsPM.models.UserPrivateInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserPrivateInfoRepository extends JpaRepository<UserPrivateInfo, UUID> {
}
