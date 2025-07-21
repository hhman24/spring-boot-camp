package com.java.spring_boot_camp.modules.users.dtos.requests;

import com.java.spring_boot_camp.modules.users.validator.DobConstraint;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;


import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor // override constructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @NotEmpty(message = "BAD_REQUEST")
    String username;

    @NotNull(message = "BAD_REQUEST")
    @Size(min = 8, message = "BAD_REQUEST")
    String password;
    String firstName;
    String lastName;

    @DobConstraint(min = 18, message = "BAD_REQUEST")
    LocalDate dob;
}
