package com.artdevs.repositories.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.artdevs.domain.entities.user.Demand;
import com.artdevs.domain.entities.user.User;

@Repository
public interface DemandRepository extends JpaRepository<Demand, Integer> {
	List<Demand> findByUser(User user);
}
