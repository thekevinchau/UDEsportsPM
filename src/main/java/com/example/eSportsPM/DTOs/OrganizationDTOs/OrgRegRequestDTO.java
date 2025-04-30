package com.example.eSportsPM.DTOs.OrganizationDTOs;

import com.example.eSportsPM.models.OrgRegistration;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
This is the object the client will send as the request body to request an organization to be registered.

 */
@Data
@NoArgsConstructor
public class OrgRegRequestDTO {
    private String orgName;
    private String orgEmail;
    private String billingEmail;
    private String description;
    private int estNumTeams;

    public OrgRegRequestDTO(OrgRegistration registration) {
        this.orgName = registration.getOrgName();
        this.orgEmail = registration.getOrgEmail();
        this.billingEmail = registration.getBillingEmail();
        this.description = registration.getDescription();
        this.estNumTeams = registration.getEstNumTeams();
    }
}
