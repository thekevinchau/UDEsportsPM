package com.example.eSportsPM.DTOs;

import lombok.Data;

import java.net.URL;

@Data
public class PublicProfileEdit {
    private String gamerTag;
    private String bio;
    private String userClass;
    private int expectedGrad;
    private URL avatarURL;
    private URL bannerURL;
}
