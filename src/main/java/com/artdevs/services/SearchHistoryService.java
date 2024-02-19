package com.artdevs.services;

import com.artdevs.domain.entities.user.SearchHistory;
import com.artdevs.domain.entities.user.User;

public interface SearchHistoryService {
	SearchHistory saveSearchHistory(String keyword);
	void deleteSearchHistory(SearchHistory SearchHistory);
	
	void deleteDulicateSearchHistory(String keyword);
}
