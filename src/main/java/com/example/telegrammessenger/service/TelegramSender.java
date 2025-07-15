    package com.example.telegrammessenger.service;

    import com.example.telegrammessenger.entity.Msg;
    import com.example.telegrammessenger.entity.User;
    import com.example.telegrammessenger.repo.UserRepository;
    import org.springframework.stereotype.Service;
    import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
    import org.telegram.telegrambots.meta.bots.AbsSender;

    @Service
    public class TelegramSender {

        private final AbsSender bot;
        private final UserRepository users;

        public TelegramSender(AbsSender bot, UserRepository users) {
            this.bot = bot;
            this.users = users;
        }

        public void toUser(Msg m) {
            User u = m.getUser();
            if (u.getChatId() == null) return;
            String text = u.getName() + ", я получил от тебя сообщение:\n" + m.getBody();
            try {
                bot.execute(SendMessage.builder()
                        .chatId(u.getChatId().toString())
                        .text(text)
                        .build());
            } catch (Exception ignored) {}
        }
    }
