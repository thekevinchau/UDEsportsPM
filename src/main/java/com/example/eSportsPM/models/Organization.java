package com.example.eSportsPM.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Getter
@Setter
@Entity
@Table(name = "organization")
@AllArgsConstructor
@NoArgsConstructor
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @OneToOne
    @JoinColumn(name="owner_id", referencedColumnName = "id")
    private User owner;

    @Column(name = "website_url")
    private String websiteUrl;

    @Column(name = "twitter_handle", length = 100)
    private String twitterHandle;

    @Column(name = "timezone", length = 100)
    private String timezone;


    @Column(name = "plan", length = 50)
    private String plan;

    @Column(name = "billing_email")
    private String billingEmail;

    @Column(name = "stripe_customer_id")
    private String stripeCustomerId;

    @Column(name = "subscription_status")
    private String subscriptionStatus;

    @Column(name = "trial_ends_at")
    private OffsetDateTime trialEndsAt;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

}
