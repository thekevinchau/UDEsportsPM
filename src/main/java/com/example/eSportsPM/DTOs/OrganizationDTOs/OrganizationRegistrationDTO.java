package com.example.eSportsPM.DTOs.OrganizationDTOs;

import com.example.eSportsPM.DTOs.UserDTOs.UserDTO;
import com.example.eSportsPM.models.OrgRegistration;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.OffsetDateTime;

@Data

/*

This class is intended to encapsulate all the data that the client-side program will render so admins can view and approve or deny it.

 */

public class OrganizationRegistrationDTO {
    private String orgName;
    private String orgEmail;
    private String requested_by;
    private String status;
    private String description;
    private int estNumTeams;
    private OffsetDateTime createdAt;

    public OrganizationRegistrationDTO(OrgRegistration registration) {
        this.orgName = registration.getOrgName();
        this.orgEmail = registration.getOrgEmail();
        this.requested_by = registration.getUser().getUsername();
        this.status = registration.getStatus();
        this.description = registration.getDescription();
        this.estNumTeams = registration.getEstNumTeams();
        this.createdAt = OffsetDateTime.now();
    }
}
