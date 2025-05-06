package com.example.eSportsPM.DTOs.UserDTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class UserProfileEdit {
    @NotNull @NotBlank private String username;
    private String pronouns;
    private String biography;
    @NotNull private Boolean outOfOffice;
    private OffsetDateTime outOfOfficeStart;
    private OffsetDateTime outOfOfficeEnd;
}
