package com.artdevs.services.impl.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.artdevs.domain.entities.message.RelationShip;
import com.artdevs.domain.entities.user.User;
import com.artdevs.dto.message.RelationShipDTO;
import com.artdevs.repositories.message.RelationshipRepository;
import com.artdevs.services.RelationshipService;

@Service
public class RelationShipServiceImpl implements RelationshipService {

	@Autowired

	UserService userservice;

	@Autowired
	RelationshipRepository relationshipRepository;

//    @Override
//    public RelationShip findRelationShipById(Integer relationshipId) {
//        Optional<RelationShip> relationShipOptional = relationshipRepository.findById(relationshipId);
//        return relationShipOptional.orElse(null);
//    }
//
//    @Override
//    public List<RelationShip> findAll() {
//        return relationshipRepository.findAll();
//    }
//

//
//    @Override
//    public RelationShip updateRelationShip(RelationShip relationShip) {
//        return relationshipRepository.save(relationShip);
//    }
//
//    @Override
//    public void deleteRelationShip(RelationShip relationShip) {
//        relationshipRepository.delete(relationShip);
//    }


//	@Override
//	public List<RelationShip> findAllUserRelationshipsWithStatus(String userId) throws Exception {
//	
//		return null;
//	}

	@Override
	public List<User> getFriendOnline() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User userlogin = userservice.findByEmail(auth.getName());
		List<RelationShip> relationWithFriendOnline = relationshipRepository
				.findRelationshipByUserIdAndStatusAndOnline(userlogin.getUserId(), 1, true);
		List<User> user1 = new ArrayList<>();
		List<User> user2 = new ArrayList<>();
		List<User> FriendIsOnline = new ArrayList<>();
		for (RelationShip relation : relationWithFriendOnline) {
			user1.add(relation.getUserOneId());
			user2.add(relation.getUserTwoId());
		}
		for (User user : user1) {
			if(user.getUserId() != userlogin.getUserId()) {
				FriendIsOnline.add(user);
			}
		}
		for (User user : user2) {
			if(user.getUserId()!= userlogin.getUserId()) {
				FriendIsOnline.add(user);
			}
		}
		
		return FriendIsOnline;
	}

	@Override
	public List<RelationShip> findRelationshipByUserIdAndStatus(String userId) throws Exception {
		return relationshipRepository.findRelationshipByUserIdAndStatus(userId, 0);
	}

	@Override
	public List<RelationShipDTO> findAllFriendCandidates(String loggedInUserId) {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public boolean createRequestForAddingFriend(String loggedInUserId, String friendCandidateId) throws Exception {
		// TODO Auto-generated method stub
		return addrelation(loggedInUserId, friendCandidateId, 0);
	}

	@Override
	public boolean removeFriend(String loggedInUserId, String friendToRemoveId) throws Exception {
		// TODO Auto-generated method stub

		RelationShip relation = relationshipRepository.findRelationshipWithFriendWithStatus(loggedInUserId,
				friendToRemoveId, 1);

		if (relation != null) {
			relationshipRepository.delete(relation);
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean acceptFriend(String loggedInUserId, String friendToAcceptId) throws Exception {
		// TODO Auto-generated method stub
		return changeStatusAndSave(loggedInUserId, friendToAcceptId, 1);
	}

	@Override
	public boolean cancelFriendshipRequest(String loggedInUserId, String friendToRejectId) throws Exception {
		// TODO Auto-generated method stub
		RelationShip relation = relationshipRepository.findRelationshipWithFriendWithStatus(loggedInUserId,
				friendToRejectId, 0);
		if (relation != null) {
			relationshipRepository.delete(relation);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<RelationShipDTO> searchUsers(String loggedInUserId, String search) {
		// TODO Auto-generated method stub

		return null;
	}

	private boolean changeStatusAndSave(String loggedInUserId, String friendId, int Status) throws Exception {

//	        User loggedInUser = this.userservice.findUserById(loggedInUserId);
//	               
//
//	        User friend = this.userservice.findUserById(friendId);

		RelationShip relationship = null;
		if (Status == 1) {
			relationship = this.relationshipRepository.findRelationshipWithFriendWithStatus(loggedInUserId, friendId,
					0);
		} else {
			relationship = this.relationshipRepository.findRelationshipWithFriendWithStatus(loggedInUserId, friendId,
					1);
		}

//	        if (!relationshipValidation.isValid(relationship)) {
//	            throw new Exception();
//	        }

//	        relationship.setActionUser(loggedInUser);
		relationship.setStatus(Status);
		relationship.setTimeRelation(new Date());
		return this.relationshipRepository.save(relationship) != null;
	}

	private boolean addrelation(String loggedInUserId, String friendId, int Status) throws Exception {
		User loggedInUser = this.userservice.findUserById(loggedInUserId);

		User friend = this.userservice.findUserById(friendId);

		RelationShip relationship = new RelationShip();

//	        if (!relationshipValidation.isValid(relationship)) {
//	            throw new Exception();
//	        }

		relationship.setActionUser(loggedInUser);
		relationship.setUserOneId(loggedInUser);
		relationship.setUserTwoId(friend);
		relationship.setStatus(Status);
		relationship.setTimeRelation(new Date());
		return this.relationshipRepository.save(relationship) != null;
	}

	@Override
	public List<RelationShip> findByUserOneIdAndUserTwoIdAndStatus(User userOneId, User userTwoId, int status) {
		// TODO Auto-generated method stub
		return relationshipRepository.findByUserOneIdAndUserTwoIdAndStatus(userOneId, userTwoId, status);
	}

}
