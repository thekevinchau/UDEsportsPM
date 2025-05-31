package com.example.eSportsPM.DTOs.Organization;

import com.example.eSportsPM.models.Organization;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationDTO {
    private UUID id;
    private String name;
    private String description;
    private String type;
    private String avatarPath;
    private String bannerPath;
    private String urlPath;
    private String region;
    private String tier;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    public OrganizationDTO(Organization organization) {
        this.id = organization.getId();
        this.name = organization.getName();
        this.description = organization.getDescription();
        this.type = organization.getType();
        this.avatarPath = organization.getAvatarPath();
        this.bannerPath = organization.getBannerPath();
        this.urlPath = organization.getUrlPath();
        this.region = organization.getRegion();
        this.tier = organization.getTier();
        this.createdAt = organization.getCreatedAt();
        this.updatedAt = organization.getUpdatedAt();
    }
}
