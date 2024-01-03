package com.artdevs.repositories.message;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.artdevs.domain.entities.message.Message;
@Repository
public interface MessageRepository extends JpaRepository<Message, String> {

}
