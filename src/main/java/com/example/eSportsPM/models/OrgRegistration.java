package com.example.eSportsPM.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "org_registration")
@Data
public class OrgRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "org_name")
    private String orgName;

    @Column(name = "org_email")
    private String orgEmail;

    @OneToOne
    @JoinColumn(name = "requested_by", referencedColumnName = "id")
    private User user;

    @Column(name = "status", columnDefinition = "VARCHAR(100) DEFAULT 'Pending'", length = 100)
    @ColumnDefault("'Pending'")
    private String status;

    @Column(name = "description")
    private String description;

    @Column(name = "est_num_teams")
    private int estNumTeams;

    @Column(name = "created_at", columnDefinition = "TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP'")
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @OneToOne
    @JoinColumn(name = "updated_by", referencedColumnName = "id")
    private User updatedBy;
}
