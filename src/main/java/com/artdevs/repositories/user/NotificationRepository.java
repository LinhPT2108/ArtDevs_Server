package com.artdevs.repositories.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.artdevs.domain.entities.user.Notification;


@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer>{
	Page<Notification> findByReceiver_UserId(String receiver, Pageable pageable);
}
