package com.example.Gym.auth;

import com.example.Gym.auth.dto.AuthRequest;
import com.example.Gym.auth.dto.AuthResponse;

public class AuthService {
    private final UserRepository userRepository;
    private final passwordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthService(UserRepository userRepository, passwordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }
    public AuthResponse signup(AuthRequest request){
if(userRepository.existsByEmail(request.getEmail())){
    throw new RuntimeException("Email already in use");
    }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        String Token = jwtService.generateToken(user.getEmail());
        return new AuthResponse(Token, user.getEmail());
    }
    
    public AuthResponse login(AuthRequest request){
        authenticationManager.authenticate(request.getEmail(), request.getPassword());
        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        String Token = jwtService.generateToken(request.getEmail());
        return new AuthResponse(Token, request.getEmail());
        
        
    }

}
