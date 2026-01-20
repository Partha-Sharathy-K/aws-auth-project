package com.partha.aws.aws_springboot_project.controller;

import com.partha.aws.aws_springboot_project.dto.*;
import com.partha.aws.aws_springboot_project.service.CognitoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class CognitoController {

    private final CognitoService service;

    // =====================================================
    // ADD USER + GROUP
    // =====================================================
    @PostMapping(
            value = "/add-user",
            consumes = "application/json",
            produces = "application/json"
    )
    public Map<String, String> addUser(@RequestBody AddUserRequest req) {

        service.createUser(
                req.getEmail(),
                req.getName(),
                req.getTemporaryPassword(),
                req.getGroup()
        );

        return Map.of("message", "User created");
    }

    // =====================================================
    // SET PERMANENT PASSWORD (ADMIN)
    // =====================================================
    @PostMapping(
            value = "/set-password",
            consumes = "application/json",
            produces = "application/json"
    )
    public Map<String, String> setPassword(
            @RequestBody SetPasswordRequest req) {

        service.setPermanentPassword(
                req.getEmail(),
                req.getNewPassword()
        );

        return Map.of("message", "Password set");
    }

    // =====================================================
    // LOGIN (PERMANENT / TEMP PASSWORD)
    // =====================================================
    @PostMapping(
            value = "/login",
            consumes = "application/json",
            produces = "application/json"
    )
    public Map<String, Object> login(
            @RequestBody LoginRequest req) {

        return service.login(
                req.getEmail(),
                req.getPassword()
        );
    }

    // =====================================================
    // TEMP PASSWORD → NEW PASSWORD
    // =====================================================
    @PostMapping(
            value = "/new-password",
            consumes = "application/json",
            produces = "application/json"
    )
    public Map<String, Object> newPassword(
            @RequestBody NewPasswordRequest req) {

        return service.setNewPassword(
                req.getEmail(),
                req.getNewPassword(),
                req.getSessionToken()
        );
    }

    // =====================================================
    // FORGOT PASSWORD (SEND OTP)
    // =====================================================
    @PostMapping(
            value = "/forgot-password",
            consumes = "application/json",
            produces = "application/json"
    )
    public Map<String, String> forgot(
            @RequestBody ForgotPasswordRequest req) {

        service.forgotPassword(req.getEmail());

        return Map.of("message", "OTP sent");
    }

    // =====================================================
    // CONFIRM FORGOT PASSWORD
    // =====================================================
    @PostMapping(
            value = "/confirm-forgot-password",
            consumes = "application/json",
            produces = "application/json"
    )
    public Map<String, String> confirmForgot(
            @RequestBody ConfirmForgotPasswordRequest req) {

        service.confirmForgotPassword(
                req.getEmail(),
                req.getOtp(),
                req.getNewPassword()
        );

        return Map.of("message", "Password reset successful");
    }

    @PostMapping("/verify-email")
    public String verifyEmail(@RequestParam String email) {
        service.verifyUserEmail(email);
        return "Email verified";
    }

    // =====================================================
// LOGOUT API
// =====================================================
    @PostMapping(
            value = "/logout",
            produces = "application/json"
    )
    public Map<String, String> logout(
            @RequestHeader("Authorization") String authorizationHeader) {

        // Expecting: "Bearer <ACCESS_TOKEN>"
        String accessToken = authorizationHeader.replace("Bearer ", "");

        service.logout(accessToken);

        return Map.of("message", "Logged out successfully");
    }

    // =====================================================
    // REVOKE REFRESH TOKEN API
    // =====================================================
    @PostMapping(
            value = "/revoke",
            consumes = "application/json",
            produces = "application/json"
    )
    public Map<String, String> revoke(
            @RequestBody RevokeTokenRequestDto req) {

        service.revokeRefreshToken(req.getRefreshToken());

        return Map.of("message", "Refresh token revoked");
    }


}
