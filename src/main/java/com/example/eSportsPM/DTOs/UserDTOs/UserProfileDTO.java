package com.example.eSportsPM.DTOs.UserDTOs;


import com.example.eSportsPM.models.UserProfile;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
public class UserProfileDTO {
    private UUID id;
    private String username;
    private String pronouns;
    private String biography;
    private Boolean outOfOffice;
    private OffsetDateTime outOfOfficeStart;
    private OffsetDateTime outOfOfficeEnd;

    public UserProfileDTO (UserProfile profile){
        this.id = profile.getId();
        this.username = profile.getUser().getUsername(); //required to have a username
        this.pronouns = profile.getPronouns() != null ? profile.getPronouns() : "";
        this.biography = profile.getBiography() != null ? profile.getBiography(): "";
        this.outOfOffice = profile.getOutOfOffice();
        this.outOfOfficeStart = profile.getOutOfOffice() ? profile.getOutOfOfficeStart() : null;
        this.outOfOfficeEnd = profile.getOutOfOffice() ? profile.getOutOfOfficeEnd() : null; //can be null
    }
}
