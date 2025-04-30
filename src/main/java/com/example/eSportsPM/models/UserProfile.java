package com.example.eSportsPM.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_profile")
public class UserProfile {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private UUID id;

    @NotNull
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "pronouns")
    private String pronouns;

    @Column(name = "biography")
    private String biography;

    @Column(name = "out_of_office")
    private Boolean outOfOffice;

    @Column(name = "out_of_office_start")
    private OffsetDateTime outOfOfficeStart;

    @Column(name = "out_of_office_end")
    private OffsetDateTime outOfOfficeEnd;
}
