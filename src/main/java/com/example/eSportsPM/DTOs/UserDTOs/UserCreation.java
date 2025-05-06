package com.example.eSportsPM.DTOs.UserDTOs;

import lombok.Data;

@Data
public class UserCreation {
    private String username;
    private String password;
    private String email;
    private String fullName;
}
