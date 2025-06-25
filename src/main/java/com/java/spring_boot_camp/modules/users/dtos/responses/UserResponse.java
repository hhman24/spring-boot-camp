package com.java.spring_boot_camp.modules.users.dtos.responses;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor // override constructor
@AllArgsConstructor
public class UserResponse {
    String username;
    String firstName;
    String lastName;
    LocalDate dob;
}
