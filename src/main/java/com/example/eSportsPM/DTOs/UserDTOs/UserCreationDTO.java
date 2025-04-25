package com.example.eSportsPM.DTOs.UserDTOs;

import lombok.Data;

@Data
public class UserCreationDTO {
    private String username;
    private String password;
    private String email;
    private String fullName;
}
