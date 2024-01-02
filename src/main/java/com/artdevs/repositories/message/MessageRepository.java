package com.artdevs.repositories.message;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artdevs.domain.entities.message.Message;

public interface MessageRepository extends JpaRepository<Message, String> {

}
