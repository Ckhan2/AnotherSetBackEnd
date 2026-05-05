package com.example.Gym.auth;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Gym.auth.dto.AuthRequest;
import com.example.Gym.auth.dto.AuthResponse;
import com.example.Gym.auth.dto.SignupRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
private static final String Auth_COOKIE_NAME = "jwt_token";
private static final long COOKIE_MAX_AGE = 7 * 24 * 60 * 60; 
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@RequestBody SignupRequest request) {
        AuthResponse response = authService.signup(request);
        ResponseCookie jwtCookie = buildAuthCookie(response.getToken());
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Set-Cookie", jwtCookie.toString())
                .body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        AuthResponse response = authService.login(request);
        ResponseCookie jwtCookie = buildAuthCookie(response.getToken());
        return ResponseEntity.ok()
                .header("Set-Cookie", jwtCookie.toString())
                .body(response);

        
    }
    private ResponseCookie buildAuthCookie(String token) {
        return ResponseCookie.from(Auth_COOKIE_NAME, token)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(COOKIE_MAX_AGE)
                .sameSite("Strict")
                .build();
    }

}
