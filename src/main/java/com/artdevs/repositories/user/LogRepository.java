package com.artdevs.repositories.user;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.artdevs.domain.entities.user.Log;

@Repository
public interface LogRepository extends JpaRepository<Log,Integer > {
	  @Query("SELECT l FROM Log l WHERE l.timeLog BETWEEN :startTime AND :endTime")
	    List<Log> findByTimeRange(@Param("startTime") Date startTime,
	    		@Param("endTime") Date endTime);
	  
	  @Query("SELECT AVG(l.timeLog - (SELECT MAX(l2.timeLog) FROM Log l2 WHERE l2.user = l.user AND l2.timeLog < l.timeLog AND l2.action = 'login')) FROM Log l WHERE l.action = 'logout'")
      Double findAverageUsageTimeForAllUsers();
}
