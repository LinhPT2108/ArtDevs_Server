package com.artdevs.repositories.post;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.artdevs.domain.entities.post.Post;
import com.artdevs.domain.entities.post.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {
	List<Report> findByPostReportId(Post postReportId);
}
