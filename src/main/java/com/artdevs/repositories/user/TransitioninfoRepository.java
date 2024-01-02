package com.artdevs.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artdevs.domain.entities.user.TransitionInfo;

public interface TransitioninfoRepository extends JpaRepository<TransitionInfo, String> {

}
