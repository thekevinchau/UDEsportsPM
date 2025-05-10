package com.example.eSportsPM.DTOs.UserProfile;

import com.example.eSportsPM.models.UserPublicInfo;
import lombok.Data;

@Data
public class UserPublicProfileDTO {
    private String gamerTag;
    private String bio;
    private String userClass;
    private Integer expectedGrad;
    private String avatarURL;
    private String bannerURL;

    public UserPublicProfileDTO(UserPublicInfo info){
        this.gamerTag = info.getGamerTag();
        this.bio = info.getBio();
        this.userClass = info.getUserClass();
        this.expectedGrad = info.getExpectedGrad();
        this.avatarURL = info.getAvatarPath();
        this.bannerURL = info.getBannerPath();
    }
}
