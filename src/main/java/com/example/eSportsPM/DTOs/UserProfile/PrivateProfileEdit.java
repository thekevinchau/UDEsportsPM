package com.example.eSportsPM.DTOs.UserProfile;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class PrivateProfileEdit {
    @NotBlank(message = "Name cannot be empty!")
    private String fullName;
    private String studentId;

    @DecimalMin(value = "0.0", inclusive = true, message = "GPA cannot be less than 0.0")
    @DecimalMax(value = "4.0", inclusive = true, message = "GPA cannot be greater than 4.0")
    private BigDecimal gpa;

    private String emergencyContactName;

    @Pattern(regexp = "^\\d{3}-\\d{3}-\\d{4}$", message = "Phone number must be in format XXX-XXX-XXXX")
    private String emergencyContactPhone;


    private String emergencyContactRelation;

    @NotNull
    private boolean available;


    private OffsetDateTime unavailableStart;
    private OffsetDateTime unavailableEnd;
}
