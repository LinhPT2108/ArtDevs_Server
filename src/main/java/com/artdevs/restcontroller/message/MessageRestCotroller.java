package com.artdevs.restcontroller.message;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artdevs.domain.entities.message.Message;
import com.artdevs.dto.ReponseDTO;
import com.artdevs.dto.message.MessageDTO;
import com.artdevs.mapper.message.MessageMapper;
import com.artdevs.repositories.message.MessageRepository;
import com.artdevs.services.MessageService;
import com.artdevs.services.UserService;
import com.artdevs.utils.Global;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(Global.path_api)
public class MessageRestCotroller {

	@Autowired
	MessageService messageService;
	@Autowired
	UserService userService;
//    @GetMapping("/message")
//    public ResponseEntity<List<MessageDTO>> getMessage() {
//        List<MessageDTO> listMessageDTO = new ArrayList<>();
//        List<Message> listMessage = messageRepository.findAll();
//        for (Message message : listMessage) {
//            listMessageDTO.add(MessageMapper.convertToMessageDTO(message));
//        }
//        return ResponseEntity.ok(listMessageDTO);
//    }

	@GetMapping("/message/{toUserId}")
	public ResponseEntity<?> getMethodName(@RequestParam Optional<Integer> p, @PathVariable String toUserId) {
		Authentication authenticate = SecurityContextHolder.getContext().getAuthentication();
		if (authenticate.getName().equals("anonymousUser")) {
			return ResponseEntity.status(HttpStatus.SC_UNAUTHORIZED).build();
		}
		try {

			Pageable pageable = PageRequest.of(p.orElse(0), Global.size_page);
			List<Message> pageMess = messageService
					.getMessageFromUserToUser(userService.findByEmail(authenticate.getName()), toUserId, pageable);
			return ResponseEntity.ok(pageMess.stream().map(MessageMapper::convertToMessageDTO).toList());
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.badRequest().build();
		}
	}

}
