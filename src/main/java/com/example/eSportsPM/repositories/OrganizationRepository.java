package com.example.eSportsPM.repositories;

import com.example.eSportsPM.models.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrganizationRepository extends JpaRepository<Organization, UUID> {
}
