package com.example.eSportsPM.DTOs;

import com.example.eSportsPM.models.Organization;
import com.example.eSportsPM.models.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrganizationDTO {
    private String name;
    private String description;
    private String owner;
    private String websiteUrl;
    private String twitterHandle;
    private String timeZone;

    public OrganizationDTO(Organization organization) {
        this.description = organization.getDescription();
        this.name = organization.getName();
        this.websiteUrl = organization.getWebsiteUrl();
        this.twitterHandle = organization.getTwitterHandle();
        this.timeZone = organization.getTimezone();
        this.owner = organization.getOwner().getUsername();
    }
}
