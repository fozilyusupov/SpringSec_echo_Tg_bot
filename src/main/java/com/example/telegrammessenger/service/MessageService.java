package com.example.telegrammessenger.service;

import com.example.telegrammessenger.dto.MsgDto;
import com.example.telegrammessenger.entity.Msg;
import com.example.telegrammessenger.entity.User;
import com.example.telegrammessenger.repo.MsgRepository;
import com.example.telegrammessenger.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final MsgRepository msgRepo;
    private final UserRepository userRepo;
    private final TelegramSender sender;

    public MessageService(MsgRepository msgRepo, UserRepository userRepo, TelegramSender sender) {
        this.msgRepo = msgRepo;
        this.userRepo = userRepo;
        this.sender = sender;
    }

    public Msg saveAndSend(String username, String body) {
        User u = userRepo.findByUsername(username).orElseThrow();
        Msg m = new Msg();
        m.setUser(u);
        m.setBody(body);
        Msg saved = msgRepo.save(m);
        sender.toUser(saved);
        return saved;
    }

    public List<MsgDto> list(String username) {
        return msgRepo.findAllByUserUsername(username)
                .stream()
                .map(m -> new MsgDto(m.getCreatedAt(), m.getBody()))
                .toList();
    }
}
