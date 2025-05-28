package com.example.eSportsPM.repositories;

import com.example.eSportsPM.models.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrgRepository extends JpaRepository<Organization, UUID> {
    Optional<Organization> findByName(String name);
    Optional<Organization> findByUrlPath(String urlPath);
}
