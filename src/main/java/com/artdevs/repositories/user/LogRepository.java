package com.artdevs.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artdevs.domain.entities.user.Log;

public interface LogRepository extends JpaRepository<Log,Integer > {

}
