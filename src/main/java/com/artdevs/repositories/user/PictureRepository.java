package com.artdevs.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.artdevs.domain.entities.user.Picture;
@Repository
public interface PictureRepository extends JpaRepository<Picture, Integer> {

}
