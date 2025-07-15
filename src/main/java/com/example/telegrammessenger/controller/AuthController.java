package com.example.telegrammessenger.controller;

import com.example.telegrammessenger.dto.AuthRequest;
import com.example.telegrammessenger.dto.AuthResponse;
import com.example.telegrammessenger.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService auth;

    public AuthController(AuthService auth) { this.auth = auth; }

//    @PostMapping("/register")
//    public ResponseEntity<String> register(@RequestBody AuthRequest req) {
//        auth.register(req.username(), req.password(),req.name());
//        return ResponseEntity.status(HttpStatus.CREATED).body("User created");
//    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest req) {
        auth.register(req.username(), req.password(), req.name());
        return ResponseEntity.status(HttpStatus.CREATED).body("User created");
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest req) {
        String token = auth.login(req.username(), req.password());
        return new AuthResponse(token);
    }
}
