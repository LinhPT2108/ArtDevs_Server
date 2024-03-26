package com.artdevs.services.impl.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.artdevs.domain.entities.message.Message;
import com.artdevs.domain.entities.user.User;
import com.artdevs.repositories.message.MessageRepository;
import com.artdevs.repositories.user.UserRepository;
import com.artdevs.services.MessageService;
import com.artdevs.services.UserService;
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageRepository messageRepository;
    @Autowired
    UserRepository userRepository;

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

	@Override
	public List<Message> getMessageFromUserToUser(User loggedInUsername, String chatUserId, Pageable pageable)throws Exception {
		User fromUser = userRepository.findById(loggedInUsername.getUserId()).get();
//		User toUser = userRepository.findById(chatUserId).get();
		List<Message> messages = messageRepository.findAllMessagesBetweenTwoUsers(fromUser.getUserId(), chatUserId);
		return messages;
	}

}
