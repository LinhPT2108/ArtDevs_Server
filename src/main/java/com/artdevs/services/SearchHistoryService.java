package com.artdevs.services;

import com.artdevs.domain.entities.user.SearchHistory;

public interface SearchHistoryService {
	SearchHistory saveSearchHistory(String keyword);
	void deleteSearchHistory(SearchHistory SearchHistory);
}
