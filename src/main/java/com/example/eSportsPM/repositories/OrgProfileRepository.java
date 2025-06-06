package com.example.eSportsPM.repositories;

import com.example.eSportsPM.models.OrgProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrgProfileRepository extends JpaRepository<OrgProfile, UUID> {
    @Query("SELECT o FROM OrgProfile o WHERE o.userId = %:currentUserId%") //will be used to fetch all Organization profiles for a particular user
    List<OrgProfile> findOrgProfilesByUser (@Param("currentUserId") UUID userId);
}
