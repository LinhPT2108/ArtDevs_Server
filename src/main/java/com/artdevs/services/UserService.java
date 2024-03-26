package com.artdevs.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import com.artdevs.domain.entities.message.RelationShip;
import com.artdevs.domain.entities.user.User;

@Service
public interface UserService {
	
	
	
	User findUserById(String userId);

	User findByEmail(String email);

	List<User> findAll();

	User saveUser(User user);

	User updateUser(User user);

	void deleteUser(User user);
	

    Optional<Page<User>> findUserByKeyword(String keyword, Pageable pageable);
    
    Optional<Page<User>> findMentorByKeyword(String keyword, Pageable pageable);

	List<User> findMentor();
	
	List<User> FindMentorIsReady();
	


	Boolean SendMatchMentor(String mentorID);

	Boolean AcceptMatchMentor(String mentorID);

	List<RelationShip> getListMatchbyUser();

	User setIsReady();

	Boolean CancelSendMatchMentor(String userid);

	List<User> findSuitableFriend();
	
	long coutUser();
	
	long coutMentor();
	

	
	List<User> getNewMentor(Date Starttime);

	

	List<User> getNewUser(Date Starttime);

	long coutUserNew();

	long coutMentorNew();

	List<User> getUserReport1();
	List<User> getUserReport2();
	List<User> getUserBand();

}
