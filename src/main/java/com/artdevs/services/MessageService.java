package com.artdevs.services;

import java.util.List;

import com.artdevs.domain.entities.message.Message;

public interface MessageService {
    Message findMessageById(String messageId);

    List<Message> findAll();

    Message saveMessage(Message message);

    Message updateMessage(Message message);

    void deleteMessage(Message message);
}
