package com.example.eSportsPM.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name="username", nullable = false)
    private String username;

    @Column(name="email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role",  nullable = false)
    private String role;

    @Column(name = "status")
    private String status;

    @Column(name = "created_at")
    private OffsetDateTime created_at;

    @Column(name= "updated_at")
    private OffsetDateTime updated_at;

}
