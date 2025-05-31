package com.example.eSportsPM.DTOs.Organization;

import com.example.eSportsPM.models.Organization;
import lombok.Data;

import java.util.UUID;


//This DTO class is most helpful for listing out the organizations rather than retireving the full entire object.

@Data
public class OrgPartialDTO {
    private UUID orgId;
    private String name;
    private String urlPath;
    private String avatarPath;

    public OrgPartialDTO(Organization organization){
        this.orgId = organization.getId();
        this.urlPath = organization.getUrlPath();
        this.name = organization.getName();
        this.avatarPath = organization.getAvatarPath();
    }
}
