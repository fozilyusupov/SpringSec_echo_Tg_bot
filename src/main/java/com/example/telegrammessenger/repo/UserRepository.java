package com.example.telegrammessenger.repo;

import com.example.telegrammessenger.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByBotToken(String botToken);

    Optional<User> findByUsername(String username);

    // ✅ Добавь вот это:
    boolean existsByUsername(String username);
}
