package com.artdevs.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.artdevs.domain.entities.message.Message;
import com.artdevs.repositories.message.MessageRepository;
import com.artdevs.services.MessageService;

public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Override
    public Message findMessageById(String messageId) {
        Optional<Message> messageOptional = messageRepository.findById(messageId);
        return messageOptional.orElse(null);
    }

    @Override
    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    @Override
    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public Message updateMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public void deleteMessage(Message message) {
        messageRepository.delete(message);
    }

}
