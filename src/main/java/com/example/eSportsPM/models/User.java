package models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Table(name = "users", schema = "public")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="username", nullable = false)
    private String username;

    @Column(name="email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "full_name", nullable = false)
    private String full_name;

    @Column(name = "role",  nullable = false)
    private String role;

    @Column(name = "status")
    private String status;

    @Column(name = "created_at")
    private LocalDateTime created_at;

    @Column(name= "updated_at")
    private LocalDateTime updated_at;


}
