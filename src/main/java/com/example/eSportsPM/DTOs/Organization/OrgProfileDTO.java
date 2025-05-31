package com.example.eSportsPM.DTOs.Organization;

import com.example.eSportsPM.DTOs.UserDTO;
import com.example.eSportsPM.models.OrgProfile;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
public class OrgProfileDTO {
    private UUID userId;
    private OrgPartialDTO organization;
    private String role;
    private String pronouns;
    private String studentClass;
    private Integer expectedGrad;
    private String aboutMe;
    private OffsetDateTime invitedDate;
    private UserDTO invitedBy;

    public OrgProfileDTO (OrgProfile profile){
        this.userId = profile.getUserId();
        this.organization = new OrgPartialDTO(profile.getOrganization());
        this.role = profile.getRole();
        this.pronouns = profile.getPronouns();
        this.studentClass = profile.getStudentClass();
        this.expectedGrad = profile.getExpectedGrad();
        this.aboutMe = profile.getAboutMe();
        this.invitedDate = profile.getInvitedDate();
        this.invitedBy = new UserDTO(profile.getInvitedBy());
    }

}
