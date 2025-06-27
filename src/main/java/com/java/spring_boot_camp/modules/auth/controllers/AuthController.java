package com.java.spring_boot_camp.modules.auth.controllers;

import com.java.spring_boot_camp.common.dtos.ApiResponse;
import com.java.spring_boot_camp.modules.auth.dtos.requests.AuthRequest;
import com.java.spring_boot_camp.modules.auth.dtos.responses.AuthResponse;
import com.java.spring_boot_camp.modules.auth.services.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor // inject container and constructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {
    AuthService authService;

    @PostMapping("/login")
    ApiResponse<AuthResponse> login(@RequestBody AuthRequest request) {
        AuthResponse res = this.authService.verifyUser(request);
        return ApiResponse.<AuthResponse>builder().data(AuthResponse.builder().accessToken(res.getAccessToken()).build()).build();
    }
}
