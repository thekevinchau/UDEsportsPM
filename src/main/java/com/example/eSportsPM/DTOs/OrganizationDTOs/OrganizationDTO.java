package com.example.eSportsPM.DTOs.OrganizationDTOs;

import com.example.eSportsPM.models.Organization;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
/*
This will serve as the data being sent for the client-side to render out.

 */
public class OrganizationDTO {
    private UUID id;
    private String name;
    private String description;
    private String owner;
    private String websiteUrl;
    private String twitterHandle;
    private String timeZone;

    public OrganizationDTO(Organization organization) {
        this.id = organization.getId();
        this.description = organization.getDescription();
        this.name = organization.getName();
        this.websiteUrl = organization.getWebsiteUrl();
        this.twitterHandle = organization.getTwitterHandle();
        this.timeZone = organization.getTimezone();
        this.owner = organization.getOwner().getUsername();
    }
}
