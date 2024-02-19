package com.artdevs.services.impl.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.artdevs.domain.entities.user.Star;
import com.artdevs.domain.entities.user.User;
import com.artdevs.repositories.user.StarRepository;
import com.artdevs.services.StarService;

@Service
public class StarServiceImpl implements StarService {

	@Autowired
	StarRepository starRepository;

	@Override
	public Star createStar(Star star) {
		// TODO Auto-generated method stub
		return starRepository.save(star);
	}

	@Override
	public Star updateStar(Star star) {
		// TODO Auto-generated method stub
		return starRepository.save(star);
	}

	@Override
	public boolean deleteStar(Star star) {
		// TODO Auto-generated method stub
		try {
			starRepository.delete(star);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}
	}

	@Override
	public Page<Star> findAllStarByUserLogged(String userLoggedEmail, Pageable pageable) {
		// TODO Auto-generated method stub
		return starRepository.findByUserSend_Email(userLoggedEmail, pageable);
	}

	@Override
	public Star findStarByUserLoggedAndMentor(User userSend, User userReceive) {
		// TODO Auto-generated method stub
		return starRepository.findByUserSendAndUserReceive(userSend, userReceive);
	}

	@Override
	public Star findById(int starId) {
		// TODO Auto-generated method stub
		return starRepository.findById(starId).get();
	}

}
