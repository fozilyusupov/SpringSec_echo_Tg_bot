package com.example.telegrammessenger.repo;

import com.example.telegrammessenger.entity.Msg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MsgRepository extends JpaRepository<Msg, Long> {
    List<Msg> findAllByUserUsername(String username);
}
