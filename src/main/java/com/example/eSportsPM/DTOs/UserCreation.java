package com.example.eSportsPM.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserCreation {
    @NotBlank
    @Pattern(regexp = "^[^\\s]+$", message = "Username cannot contain spaces")
    private String username;

    @NotBlank
    @Pattern(regexp = "^[^\\s]+$", message = "Password cannot contain spaces")
    private String password;

    @NotBlank
    @Pattern(regexp = "^[^\\s]+$", message = "Email cannot contain spaces")
    private String email;

    @NotBlank
    private String fullName;

    @NotBlank
    private String gamerTag;
}
