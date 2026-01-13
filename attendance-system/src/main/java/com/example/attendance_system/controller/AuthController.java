package com.example.attendance_system.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.attendance_system.dto.AuthRequest;
import com.example.attendance_system.dto.AuthResponse;
import com.example.attendance_system.dto.RefreshTokenRequest;
import com.example.attendance_system.entity.RefreshToken;
import com.example.attendance_system.security.JwtUtil;
import com.example.attendance_system.service.AuthenticationService;
import com.example.attendance_system.service.RefreshTokenService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationService authService;
    private final RefreshTokenService refreshTokenService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationService authService,
            RefreshTokenService refreshTokenService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.refreshTokenService = refreshTokenService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        System.out.println("Intentando login para: " + request.getUsername()); // Log temporal
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest request) {
        // Validar si el request o el token son nulos antes de buscar en BD
        if (request.getRefreshToken() == null || request.getRefreshToken().isEmpty()) {
            return ResponseEntity.status(401).body("Refresh token is missing");
        }

        return refreshTokenService.findByToken(request.getRefreshToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String accessToken = jwtUtil.generateToken(user.getUsername());
                    return ResponseEntity.ok(new AuthResponse(accessToken, request.getRefreshToken()));
                })
                // En lugar de RuntimeException, devolvemos un error HTTP limpio
                .orElseGet(() -> ResponseEntity.status(401).build());
    }
}
