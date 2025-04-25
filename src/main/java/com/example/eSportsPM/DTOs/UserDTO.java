package com.example.eSportsPM.DTOs;

import com.example.eSportsPM.models.User;
import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private String email;
    private String fullName;
    private String organization;

    public UserDTO(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.fullName = user.getFull_name();
        this.organization = user.getOrganization().getName();
    }
}
