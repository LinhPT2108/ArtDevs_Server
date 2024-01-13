package com.artdevs.repositories.message;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.artdevs.domain.entities.message.PictureOfMessage;

@Repository
public interface ImageOfMessageRepository extends JpaRepository<PictureOfMessage, Long>{

}
