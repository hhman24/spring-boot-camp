package com.java.spring_boot_camp.modules.users.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Role {
    @Id
    private String Name;

    private String Description;

    @ManyToMany
    Set<Permission> permissions;
}
