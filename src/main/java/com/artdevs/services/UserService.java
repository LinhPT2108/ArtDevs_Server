package com.artdevs.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.artdevs.domain.entities.message.RelationShip;
import com.artdevs.domain.entities.user.TransitionInfo;
import com.artdevs.domain.entities.user.User;

@Service
public interface UserService {
	User findUserById(String userId);

	User findByEmail(String email);

	List<User> findAll();

	User saveUser(User user);

	User updateUser(User user);

	void deleteUser(User user);

	List<User> findMentor();
	
	List<User> FindMentorIsReady();


	Boolean SendMatchMentor(String mentorID);

	Boolean AcceptMatchMentor(String mentorID);

	List<User> getListMatchbyUser();

	User setIsReady();

	Boolean CancelSendMatchMentor(String userid);

}
