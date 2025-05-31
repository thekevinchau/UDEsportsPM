package com.example.eSportsPM.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserCreation {
    @NotBlank
    @Pattern(regexp = "^[^\\s]+$", message = "Username cannot contain spaces")
    private String username;

    @NotBlank
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).{12,}$",
            message = "Password must be at least 12 characters long and include at least one uppercase letter, one lowercase letter, and one special character."
    )
    private String password;

    @NotBlank
    @Pattern(
            regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",
            message = "Invalid email format"
    )
    private String email;

    @NotBlank
    @Pattern(
            regexp = "^[A-Z][a-z]+(?:[-'][A-Z][a-z]+)?\\s[A-Z][a-z]+(?:[-'][A-Z][a-z]+)?$",
            message = "Name must be in 'Firstname Lastname' format, starting with capital letters"
    )
    private String fullName;
}
