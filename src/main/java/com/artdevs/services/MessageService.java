package com.artdevs.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.artdevs.domain.entities.message.Message;
import com.artdevs.domain.entities.user.User;

public interface MessageService {
    Message findMessageById(String messageId);

    List<Message> findAll();

    Message saveMessage(Message message);

    Message updateMessage(Message message);

    void deleteMessage(Message message);
    
    List<Message> getMessageFromUserToUser(User loggedInUsername, String chatUserId, Pageable pageable) throws Exception;
}
