package com.artdevs.repositories.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.artdevs.domain.entities.user.Notification;
import com.artdevs.domain.entities.user.User;


@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer>{
	List<Notification> findByReceiver(User receiver);
	
	Page<Notification> findByReceiver_UserId(String receiver, Pageable pageable);
	
	long countByReceiver_UserIdAndIsRead(String receiver_UserId, boolean read);
}
