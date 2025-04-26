package com.example.eSportsPM.DTOs.OrganizationDTOs;

import lombok.Data;

/*
This is the object the client will send as the request body to request an organization to be registered.

 */
@Data
public class OrgRegRequestDTO {
    private String orgName;
    private String orgEmail;
    private String description;
    private int estNumTeams;
}
