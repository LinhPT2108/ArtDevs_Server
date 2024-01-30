package com.artdevs.services;

import java.util.List;

import com.artdevs.domain.entities.message.RelationShip;
import com.artdevs.domain.entities.user.User;
import com.artdevs.dto.message.RelationShipDTO;

public interface RelationshipService {
	List<RelationShip> findByUserOneIdAndUserTwoIdAndStatus(User userOneId,User userTwoId, int status);
	List<RelationShipDTO> findAllUserRelationshipsWithStatus(String userId) throws Exception;

    List<RelationShipDTO> findAllFriendCandidates(String loggedInUserId);

    boolean createRequestForAddingFriend(String loggedInUserId, String friendCandidateId) throws Exception;

    boolean removeFriend(String loggedInUserId, String friendToRemoveId) throws Exception;

    boolean acceptFriend(String loggedInUserId, String friendToAcceptId) throws Exception;

    boolean cancelFriendshipRequest(String loggedInUserId, String friendToRejectId) throws Exception;

    List<RelationShipDTO> searchUsers(String loggedInUserId, String search);
}
