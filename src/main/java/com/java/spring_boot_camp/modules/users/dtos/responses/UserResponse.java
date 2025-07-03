package com.java.spring_boot_camp.modules.users.dtos.responses;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor // override constructor
@AllArgsConstructor
public class UserResponse {
    String username;
    String firstName;
    String lastName;
    LocalDate dob;
    Set<String> roles;
}
