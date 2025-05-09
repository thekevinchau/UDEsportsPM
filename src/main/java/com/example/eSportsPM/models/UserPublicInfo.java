package com.example.eSportsPM.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "user_public_info")
public class UserPublicInfo {
    @Id
    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "gamertag", nullable = false)
    private String gamerTag;

    @Column(name = "expected_grad")
    private Integer expectedGrad;

    @Column(name = "bio", columnDefinition = "TEXT")
    private String bio;

    @Column(name = "class")
    private String userClass;  // 'class' is a reserved keyword in Java

    @Column(name = "avatar_path")
    private String avatarPath;

    @Column(name = "banner_path")
    private String bannerPath;

    @Column(name = "public_profile_path")
    private String publicProfilePath;
}
