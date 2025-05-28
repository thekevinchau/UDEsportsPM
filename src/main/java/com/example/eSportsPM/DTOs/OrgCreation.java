package com.example.eSportsPM.DTOs;

import lombok.Data;

@Data
public class OrgCreation {
    private String name;
    private String description;
    private String type;
    private String avatarPath;
    private String bannerPath;
    private String region;
}
