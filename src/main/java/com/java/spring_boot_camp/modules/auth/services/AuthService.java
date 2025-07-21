package com.java.spring_boot_camp.modules.auth.services;

import com.java.spring_boot_camp.common.enums.ErrorCode;
import com.java.spring_boot_camp.common.exceptions.AppException;
import com.java.spring_boot_camp.modules.auth.dtos.requests.AuthRequest;
import com.java.spring_boot_camp.modules.auth.dtos.responses.AuthResponse;
import com.java.spring_boot_camp.modules.users.dtos.responses.UserResponse;
import com.java.spring_boot_camp.modules.users.entities.User;
import com.java.spring_boot_camp.modules.users.services.UserService;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Date;
import java.util.StringJoiner;


@Slf4j
@Service
@RequiredArgsConstructor // inject container and constructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthService {
    UserService userService;

    //    @NonFinal // not inject to constructor
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY = "3KZrhQJwf+6v8MZb9xFfVeIq9W8L9AFyN5P8VXHz+MkGRjCtcB8ABg==";

    public AuthResponse verifyUser(AuthRequest request) {
        try {
            User user = userService.getUserByUsernamePassword(request.getUsername());

            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
            boolean isValid = passwordEncoder.matches(request.getPassword(), user.getPassword());

            if (!isValid) {
                throw new AppException(ErrorCode.BAD_REQUEST);
            }

            String accessToken = this.generateToken(user);

            return AuthResponse.builder().accessToken(accessToken).build();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private String generateToken(User user) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS384);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("hhman")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
                .claim("scope", buildScope(user)).build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("can not create token:", e);
            throw new RuntimeException(e);
        }
    }

    private boolean verifyToken(String token) throws JOSEException, ParseException {

        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        var isSuccess = signedJWT.verify(verifier);

        return isSuccess && expiryTime.after(new Date());
    }

    private String buildScope(User user) {
        StringJoiner stringJoiner = new StringJoiner(" ");


        if (!CollectionUtils.isEmpty(user.getRoles())) {
            user.getRoles().forEach(role -> {
                stringJoiner.add("ROLE_" + role.getName());
                if (!CollectionUtils.isEmpty(role.getPermissions())) {
                    role.getPermissions().forEach(permission -> stringJoiner.add(permission.getName()));
                }
            });
        };

        return stringJoiner.toString();
    }
}
