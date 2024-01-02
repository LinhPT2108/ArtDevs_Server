package com.artdevs.repositories.post;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artdevs.domain.entities.post.Report;

public interface ReportRepository extends JpaRepository<Report, Integer> {

}
