package com.example.eSportsPM.models;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "org_profile")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrgProfile {

    @Id
    @Column(name = "org_profile_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID orgProfileId;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id")
    private Organization organization;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "pronouns")
    private String pronouns;

    @Column(name = "class")
    private String studentClass;

    @Column(name = "expected_grad")
    private Integer expectedGrad;

    @Column(name = "about_me")
    private String aboutMe;

    @Column(name = "invited_date")
    private OffsetDateTime invitedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invited_by")
    private User invitedBy;

}
