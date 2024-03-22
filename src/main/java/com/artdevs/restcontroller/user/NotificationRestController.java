package com.artdevs.restcontroller.user;

import org.springframework.web.bind.annotation.RestController;

import com.artdevs.domain.entities.user.Demand;
import com.artdevs.domain.entities.user.Notification;
import com.artdevs.domain.entities.user.User;
import com.artdevs.dto.user.NotificationDTO;
import com.artdevs.dto.user.NotificationToGetDTO;
import com.artdevs.mapper.DemandMapper;
import com.artdevs.mapper.NotificationMapper;
import com.artdevs.services.NotificationService;
import com.artdevs.services.UserService;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	@Autowired
	UserService userService;

	@GetMapping("/notifications/{userId}")
	public ResponseEntity<List<NotificationToGetDTO>> getMethodName(@PathVariable("userId") String userId,
			@RequestParam("p") Optional<Integer> p) {
		System.out.println("page size: " + p);
		Pageable pageable = PageRequest.of(p.orElse(0), 7, Sort.by("createDate").descending());
		Page<Notification> notifications = notificationService.findByUserId(userId, pageable);
		List<NotificationToGetDTO> notificationDTOs = notifications.stream().map(NotificationMapper::convertToGetDto)
				.collect(Collectors.toList());
		return ResponseEntity.ok(notificationDTOs);
	}

	@GetMapping("/count-notifications-unread")
	public ResponseEntity<?> getMethodName() {
		Authentication authenticate = SecurityContextHolder.getContext().getAuthentication();
		if (!authenticate.getName().equals("anonymousUser")) {
			User userLogged = userService.findByEmail(authenticate.getName());
			long countUnRead = notificationService.countNotificationUnReadByUserId(userLogged.getUserId());

			System.out.println("count Unread: " + countUnRead);
			return ResponseEntity.ok(countUnRead);
		} else {
			return ResponseEntity.ok(HttpStatus.UNAUTHORIZED);
		}
	}

	@PutMapping("/notifications/{id}")
	public ResponseEntity<?> putNotificationIsRead(@PathVariable("id") String notificationId, @RequestParam("read") boolean read) {
		try {
			System.out.println(read);
			Notification notification = notificationService.findById(Integer.parseInt(notificationId));
			notification.setRead(read);
			notificationService.updateNotification(notification);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@PutMapping("/read-all-notifications")
	public ResponseEntity<?> putMethodName() {
		Authentication authenticate = SecurityContextHolder.getContext().getAuthentication();
		if (authenticate.getName().equals("anonymousUser")) {
			return ResponseEntity.ok(HttpStatus.UNAUTHORIZED);
		}
		try {
			User userLogged = userService.findByEmail(authenticate.getName());
			notificationService.updateAllNotificationReadTrueByUser(userLogged);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.internalServerError().build();
		}
		
	}

	@DeleteMapping("/delete-notifications/{id}")
	public ResponseEntity<?> getMethodName(@PathVariable("id") String notificationId) {
		Authentication authenticate = SecurityContextHolder.getContext().getAuthentication();
		if (!authenticate.getName().equals("anonymousUser")) {
			try {
			Notification notification = notificationService.findById(Integer.parseInt(notificationId));
				notificationService.deleteNotification(notification);
			} catch (Exception e) {
				return ResponseEntity.badRequest().build();
			}
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.ok(HttpStatus.UNAUTHORIZED);
		}
	}
	
}
