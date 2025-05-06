package com.example.eSportsPM.DTOs.UserDTOs;

import com.example.eSportsPM.models.User;
import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private String email;

    public UserDTO(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();

    }
}
