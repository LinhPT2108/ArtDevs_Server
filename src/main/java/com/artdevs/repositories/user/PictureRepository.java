package com.artdevs.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artdevs.domain.entities.user.Picture;

public interface PictureRepository extends JpaRepository<Picture, Integer> {

}
