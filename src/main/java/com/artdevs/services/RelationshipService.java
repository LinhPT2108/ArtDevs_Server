package com.artdevs.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.artdevs.domain.entities.message.RelationShip;
import com.artdevs.domain.entities.user.User;
import com.artdevs.dto.message.RelationShipDTO;

@Service
public interface RelationshipService {

//	List<RelationShip> findAllUserRelationshipsWithStatus(String userId) throws Exception;

	RelationShip findByUserOneIdAndUserTwoIdAndStatus(User userOneId,User userTwoId, int status);
//	List<RelationShipDTO> findAllUserRelationshipsWithStatus(String userId) throws Exception;


    List<RelationShipDTO> findAllFriendCandidates(String loggedInUserId);



    boolean removeFriend(String loggedInUserId, String friendToRemoveId) throws Exception;

    boolean acceptFriend(String loggedInUserId, String friendToAcceptId) throws Exception;

    boolean cancelFriendshipRequest(String loggedInUserId, String friendToRejectId) throws Exception;

    List<RelationShipDTO> searchUsers(String loggedInUserId, String search);

	List<RelationShip> findRelationshipByUserIdAndStatus(String userId) throws Exception;


	boolean createRequestForAddingFriend(String loggedInUserId, String friendCandidateId) throws Exception;



	List<User> getFriendOnline();
	
	List<User> getAllFriend();
	
	List<User> getAllMentor();


	boolean removeUserOfListSuitable(String loggedInUserId, String friendId, int Status);
}
