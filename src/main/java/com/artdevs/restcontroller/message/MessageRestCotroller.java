package com.artdevs.restcontroller.message;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artdevs.domain.entities.message.Message;
import com.artdevs.dto.message.MessageDTO;
import com.artdevs.mapper.message.MessageMapper;
import com.artdevs.repositories.message.MessageRepository;
import com.artdevs.services.MessageService;
import com.artdevs.utils.Global;

@RestController
@RequestMapping(Global.path_api)
public class MessageRestCotroller {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    MessageService messageService;

    @GetMapping("/message")
    public ResponseEntity<List<MessageDTO>> getMessage() {
        List<MessageDTO> listMessageDTO = new ArrayList<>();
        List<Message> listMessage = messageRepository.findAll();
        for (Message message : listMessage) {
            listMessageDTO.add(MessageMapper.convertToMessageDTO(message));
        }
        return ResponseEntity.ok(listMessageDTO);
    }
}
