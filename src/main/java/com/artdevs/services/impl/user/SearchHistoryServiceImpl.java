package com.artdevs.services.impl.user;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.artdevs.domain.entities.user.SearchHistory;
import com.artdevs.domain.entities.user.User;
import com.artdevs.repositories.user.SearchHistoryRepository;
import com.artdevs.services.SearchHistoryService;
import com.artdevs.services.UserService;

@Service
public class SearchHistoryServiceImpl implements SearchHistoryService {

	@Autowired
	SearchHistoryRepository historyRepository;
	@Autowired
	UserService userService;

	@Override
	public SearchHistory saveSearchHistory(String keyword) {
		Authentication authenticate = SecurityContextHolder.getContext().getAuthentication();
		String loggedInUserEmail = authenticate.getName();
		if(!loggedInUserEmail.equals("anonymousUser")) {
			User user = userService.findByEmail(loggedInUserEmail);
			
			SearchHistory saveSearchHistory = new SearchHistory();
			saveSearchHistory.setKeyword(keyword);
			saveSearchHistory.setCreateDate(new Date());
			saveSearchHistory.setUser(user);
			return historyRepository.save(saveSearchHistory);			
		}else {
			return null;
		}
	}

	@Override
	public void deleteSearchHistory(SearchHistory SearchHistory) {
		historyRepository.delete(SearchHistory);
	}

}
