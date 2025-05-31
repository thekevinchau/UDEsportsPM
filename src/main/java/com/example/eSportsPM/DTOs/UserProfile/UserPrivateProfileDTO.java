package com.example.eSportsPM.DTOs.UserProfile;

import com.example.eSportsPM.models.UserPrivateInfo;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class UserPrivateProfileDTO {
    @NotNull
    private String fullName;
    private String studentId;
    private BigDecimal gpa;

    @NotNull
    private boolean eligibility;

    private String emergencyContactName;
    private String emergencyContactPhone;
    private String emergencyContactRelation;
    @NotNull

    private boolean available;
    private OffsetDateTime unavailableStart;
    private OffsetDateTime unavailableEnd;

    public UserPrivateProfileDTO(UserPrivateInfo info) {
        this.fullName = info.getFullName();
        this.studentId = info.getStudentId();
        this.gpa = info.getGpa();
        this.eligibility = info.getEligible();
        this.emergencyContactName = info.getEmergencyContactName();
        this.emergencyContactPhone = info.getEmergencyContactPhone();
        this.emergencyContactRelation = info.getEmergencyContactRelation();
        this.available = info.getAvailable();
        this.unavailableStart = info.getUnavailableStart();
        this.unavailableEnd = info.getUnavailableEnd();
    }
}
