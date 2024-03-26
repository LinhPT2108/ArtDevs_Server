package com.artdevs.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.artdevs.domain.entities.user.Notification;
import com.artdevs.domain.entities.user.User;

public interface NotificationService {
	
	Notification createNotification(Notification notification);
	Notification updateNotification(Notification notification);
	void updateAllNotificationReadTrueByUser(User user) throws Exception;
	Page<Notification> findByUserId(String userId, Pageable pageable);
	long countNotificationUnReadByUserId(String userId);
	void deleteNotification(Notification notification) throws Exception;
	Notification findById(int id);
}
