package com.example.eSportsPM.DTOs.UserProfile;

import lombok.Data;

@Data
public class PublicProfileEdit {
    private String gamerTag;
    private String bio;
    private String userClass;
    private Integer expectedGrad;
    private String avatarURL;
    private String bannerURL;
}
