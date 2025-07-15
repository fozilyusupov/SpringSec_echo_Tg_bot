package com.example.telegrammessenger.controller;

import com.example.telegrammessenger.dto.MsgDto;
import com.example.telegrammessenger.service.MessageService;
import jakarta.validation.constraints.Size;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService service;

    public MessageController(MessageService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> send(@RequestBody @Size(max=1024) String body, Principal p) {
        service.saveAndSend(p.getName(), body);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<MsgDto> list(Principal p) {
        return service.list(p.getName());
    }
}
