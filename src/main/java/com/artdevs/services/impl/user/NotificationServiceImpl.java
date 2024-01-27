package com.artdevs.services.impl.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.artdevs.domain.entities.user.Notification;
import com.artdevs.repositories.user.NotificationRepository;
import com.artdevs.services.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService{

	@Autowired
	NotificationRepository notificationRepository;
	
	@Override
	public Notification createNotification(Notification notification) {
		return notificationRepository.save(notification);
	}

	@Override
	public Notification updateNotification(Notification notification) {
		// TODO Auto-generated method stub
		return notificationRepository.save(notification);
	}

	@Override
	public Page<Notification> findByUserId(String userId, Pageable pageable) {
		// TODO Auto-generated method stub
		return notificationRepository.findByReceiver_UserId(userId, pageable);
	}

	@Override
	public int countNotificationUnReadByUserId(String userId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Notification findById(int id) {
		// TODO Auto-generated method stub
		return notificationRepository.findById(id).get();
	}

}
