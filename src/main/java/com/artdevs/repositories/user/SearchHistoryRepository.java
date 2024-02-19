package com.artdevs.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.artdevs.domain.entities.user.SearchHistory;
import com.artdevs.domain.entities.user.User;

@Repository
public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Integer>{
	void deleteByKeywordAndUser(String keyword, User user);
}
