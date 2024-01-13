package com.artdevs.services.impl.user;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artdevs.domain.entities.user.SearchHistory;
import com.artdevs.repositories.user.SearchHistoryRepository;
import com.artdevs.services.SearchHistoryService;

@Service
public class SearchHistoryServiceImpl implements SearchHistoryService{

	@Autowired
	SearchHistoryRepository historyRepository;
	
	@Override
	public SearchHistory saveSearchHistory(String keyword) {
		SearchHistory saveSearchHistory = new SearchHistory();
		saveSearchHistory.setKeyword(keyword);
		saveSearchHistory.setCreateDate(new Date());
//		saveSearchHistory.setUser();
		return historyRepository.save(null);
	}

	@Override
	public void deleteSearchHistory(SearchHistory SearchHistory) {
		historyRepository.delete(SearchHistory);
	}

}
