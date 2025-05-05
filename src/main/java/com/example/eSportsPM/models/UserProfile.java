package com.example.eSportsPM.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_profile")
public class UserProfile {

    @Id
    @Column(name = "user_id")
    private UUID id;

    @OneToOne
    @MapsId // tells JPA to use this as both FK and PK
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "full_name")
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
