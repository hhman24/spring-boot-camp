package com.java.spring_boot_camp.modules.users.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private LocalDate dob;

    @ManyToMany
    private Set<Role> roles;
}
