package com.example.attendance_system.service;

import com.example.attendance_system.dto.AuthRequest;
import com.example.attendance_system.dto.AuthResponse;
import com.example.attendance_system.entity.RefreshToken;
import com.example.attendance_system.security.JwtUtil;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;

    public AuthenticationService(AuthenticationManager authenticationManager,
            JwtUtil jwtUtil, RefreshTokenService refreshTokenService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.refreshTokenService = refreshTokenService;
    }

    public AuthResponse login(AuthRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(),
                            request.getPassword()));
        } catch (BadCredentialsException e) {
            System.out.println("ERROR: La contraseña no coincide o el usuario no existe");
            throw e;
        }
        String accessToken = jwtUtil.generateToken(request.getUsername());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(request.getUsername());

        return new AuthResponse(accessToken, refreshToken.getToken());
    }
}