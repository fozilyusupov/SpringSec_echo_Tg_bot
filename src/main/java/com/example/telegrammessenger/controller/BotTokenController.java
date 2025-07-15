package com.example.telegrammessenger.controller;

import com.example.telegrammessenger.repo.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/api/bot")
public class BotTokenController {

    private final UserRepository users;
    public BotTokenController(UserRepository users) { this.users = users; }

    @PostMapping("/token")
    public String generate(Principal p) {
        System.out.println("👤 Principal: " + p); // Должен быть не null
        var u = users.findByUsername(p.getName()).orElseThrow();
        if (u.getBotToken() == null) {
            u.setBotToken(UUID.randomUUID().toString());
            users.save(u);
        }
        return u.getBotToken();
    }
}
