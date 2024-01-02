package com.artdevs.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artdevs.domain.entities.user.Demand;

public interface DemandRepository extends JpaRepository<Demand, Integer> {

}
