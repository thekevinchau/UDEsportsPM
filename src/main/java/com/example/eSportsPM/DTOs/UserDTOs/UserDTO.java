package com.example.eSportsPM.DTOs.UserDTOs;

import com.example.eSportsPM.models.User;
import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private String email;
    private String organization;

    public UserDTO(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        if (user.getOrganization() != null){
            this.organization = user.getOrganization().getName();
        }
        this.organization = "";

    }
}
