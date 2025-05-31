package com.example.eSportsPM.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "org_id", referencedColumnName = "id")
    private Organization organization;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "game")
    private String game;

    @Column(name = "division")
    private String division;

    @Column(name = "logo_url")
    private String logoUrl;

    @CreationTimestamp
    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;
}
