package com.artdevs.restcontroller.user;

import org.springframework.web.bind.annotation.RestController;

import com.artdevs.domain.entities.user.Notification;
import com.artdevs.dto.user.NotificationDTO;
import com.artdevs.mapper.NotificationMapper;
import com.artdevs.services.NotificationService;
import com.artdevs.utils.Global;
import com.google.protobuf.Option;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(Global.path_api)
public class NotificationRestController {

	@Autowired
	NotificationService notificationService;

	@GetMapping("/notifications/{userId}")
	public ResponseEntity<List<NotificationDTO>> getMethodName(@PathVariable("userId") String userId,
			@RequestParam("p") Optional<Integer> p) {
		Pageable pageable = PageRequest.of(p.orElse(0), 7, Sort.by("createDate").descending());
		Page<Notification> notifications = notificationService.findByUserId(userId, pageable);
		List<NotificationDTO> notificationDTOs = notifications.stream().map(NotificationMapper::convertToDto)
				.collect(Collectors.toList());
		return ResponseEntity.ok(notificationDTOs);
	}
	
	@PutMapping("/notifications/{id}")
	public ResponseEntity<?> putNotificationIsRead(@PathVariable("id") String notificationId) {
		try {
			Notification notification = notificationService.findById(Integer.parseInt(notificationId));
			notificationService.createNotification(notification);
			return ResponseEntity.ok().build();				
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.internalServerError().build();	
		}
	}

}
