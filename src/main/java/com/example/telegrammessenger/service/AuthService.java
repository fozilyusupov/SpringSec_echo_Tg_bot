package com.example.telegrammessenger.service;

import com.example.telegrammessenger.entity.User;
import com.example.telegrammessenger.repo.UserRepository;
import com.example.telegrammessenger.security.JwtService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final JwtService jwt;

    public AuthService(UserRepository repo, PasswordEncoder encoder, JwtService jwt) {
        this.repo = repo;
        this.encoder = encoder;
        this.jwt = jwt;
    }

    public void register(String username, String rawPassword, String name) {
        User u = new User();
        u.setUsername(username);
        u.setPassword(encoder.encode(rawPassword));
        u.setName(name);

        repo.save(u);
    }

    public String login(String username, String rawPassword) {
        User u = repo.findByUsername(username)
                .orElseThrow(() -> new BadCredentialsException("Wrong user"));
        if (!encoder.matches(rawPassword, u.getPassword())) {
            throw new BadCredentialsException("Wrong password");
        }
        return jwt.generate(username);
    }
}
