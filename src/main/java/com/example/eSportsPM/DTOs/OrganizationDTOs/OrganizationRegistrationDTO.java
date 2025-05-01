package com.example.eSportsPM.DTOs.OrganizationDTOs;

import com.example.eSportsPM.DTOs.UserDTOs.UserDTO;
import com.example.eSportsPM.models.OrgRegistration;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data

/*

This class is intended to encapsulate all the data that the client-side program will render so admins can view and approve or deny it.

 */

public class OrganizationRegistrationDTO {
    private UUID id;
    private String orgName;
    private String orgEmail;
    private String requested_by;
    private String billingEmail;
    private String status;
    private String description;
    private int estNumTeams;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private String updatedBy;

    public OrganizationRegistrationDTO(OrgRegistration registration) {
        this.id = registration.getId();
        this.orgName = registration.getOrgName();
        this.orgEmail = registration.getOrgEmail();
        this.requested_by = registration.getRequestedBy().getUsername();
        this.billingEmail = registration.getBillingEmail();
        this.status = registration.getStatus();
        this.description = registration.getDescription();
        this.estNumTeams = registration.getEstNumTeams();
        this.createdAt = OffsetDateTime.now();
        this.updatedAt = registration.getUpdatedAt();
        this.updatedBy = registration.getUpdatedBy() == null ? "" : registration.getUpdatedBy().getUsername();
    }
}
