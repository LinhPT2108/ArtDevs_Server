package com.artdevs.repositories.message;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.artdevs.domain.entities.message.Message;
@Repository
public interface MessageRepository extends JpaRepository<Message, String> {
	@Query(value = "" +
            "SELECT m FROM Message AS m " +
            "WHERE ((m.formUserId.userId = :firstUserId AND m.toUserId.userId = :secondUserId) " +
            "OR  (m.formUserId.userId = :secondUserId AND m.toUserId.userId = :firstUserId)) " +
            "ORDER BY m.timeMessage")
    List<Message> findAllMessagesBetweenTwoUsers(@Param("firstUserId") String firstUserId, @Param("secondUserId") String secondUserId);
	
}
