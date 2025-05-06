package com.example.eSportsPM.models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "user_private_info")
public class UserPrivateInfo {
    @Id
    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "full_name", nullable = false, length = 255)
    private String fullName;

    @Column(name = "student_id", length = 255)
    private String studentId;

    @Column(name = "gpa", precision = 5, scale = 2)
    private BigDecimal gpa;

    @Column(name = "eligibility")
    private Boolean eligibility;

    @Column(name = "emergency_contact_name", length = 255)
    private String emergencyContactName;

    @Column(name = "emergency_contact_phone", length = 255)
    private String emergencyContactPhone;

    @Column(name = "emergency_contact_relation", length = 255)
    private String emergencyContactRelation;

    @Column(name = "availabile")
    private Boolean availabile = false;

    @Column(name = "unavailable_start")
    private OffsetDateTime unavailableStart;

    @Column(name = "unavailable_end")
    private OffsetDateTime unavailableEnd;
}
