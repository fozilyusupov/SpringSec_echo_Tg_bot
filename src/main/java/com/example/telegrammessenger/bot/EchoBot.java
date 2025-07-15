package com.example.telegrammessenger.bot;

import com.example.telegrammessenger.repo.UserRepository;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class EchoBot extends TelegramLongPollingBot {

    private final UserRepository users;

    private final BotConfig config;

    public EchoBot(UserRepository users, BotConfig config) {
        this.users = users;
        this.config=config;
    }


    @Override
    public String getBotUsername() { return config.getName(); }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println("dasdas");
        if (!update.hasMessage() || !update.getMessage().hasText()) return;
        String text = update.getMessage().getText().trim();
        Long chatId = update.getMessage().getChatId();

        users.findByBotToken(text).ifPresent(user -> {
            user.setChatId(chatId);
            users.save(user);
            try {
                execute(SendMessage.builder()
                        .chatId(chatId.toString())
                        .text("✅ Токен привязан. Теперь отправляйте сообщения через сайт.")
                        .build());
            } catch (TelegramApiException ignored) {}
        });

    }
}
