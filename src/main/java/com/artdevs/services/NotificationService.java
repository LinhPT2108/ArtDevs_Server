package com.artdevs.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.artdevs.domain.entities.user.Notification;

public interface NotificationService {
	
	Notification createNotification(Notification notification);
	Notification updateNotification(Notification notification);
	Page<Notification> findByUserId(String userId, Pageable pageable);
	int countNotificationUnReadByUserId(String userId);
	
	Notification findById(int id);
}
