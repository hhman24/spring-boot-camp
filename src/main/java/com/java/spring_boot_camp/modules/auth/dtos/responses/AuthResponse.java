package com.java.spring_boot_camp.modules.auth.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor // override constructor
@AllArgsConstructor
public class AuthResponse {
    String accessToken;
}
