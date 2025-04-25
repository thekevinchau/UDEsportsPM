package com.example.eSportsPM.DTOs;

import lombok.Data;

@Data
public class UserCreationDTO {
    private String username;
    private String password;
    private String email;
    private String fullName;
}
