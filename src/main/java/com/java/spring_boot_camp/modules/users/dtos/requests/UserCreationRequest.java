package com.java.spring_boot_camp.modules.users.dtos.requests;

import jakarta.validation.constraints.NotEmpty;
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
    String username;

    @Size(min = 8, message = "BAD_REQUEST")
    @NotEmpty()
    String password;
    String firstName;
    String lastName;
    LocalDate dob;

}
