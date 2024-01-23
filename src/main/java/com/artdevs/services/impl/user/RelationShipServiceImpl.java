package com.artdevs.services.impl.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artdevs.domain.entities.message.RelationShip;
import com.artdevs.dto.message.RelationShipDTO;
import com.artdevs.repositories.message.RelationshipRepository;
import com.artdevs.services.RelationshipService;
@Service
public class RelationShipServiceImpl implements RelationshipService {

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
//    @Override
//    public RelationShip saveRelationShip(RelationShip relationShip) {
//        return relationshipRepository.save(relationShip);
//    }
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

	@Override
	public List<RelationShipDTO> findAllUserRelationshipsWithStatus(String userId) throws Exception {
		// TODO Auto-generated method stub
		List<RelationShip> listRelationDTO = relationshipRepository.findAll();
		return null;
	}

	@Override
	public List<RelationShipDTO> findAllFriendCandidates(String loggedInUserId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createRequestForAddingFriend(String loggedInUserId, String friendCandidateId) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeFriend(String loggedInUserId, String friendToRemoveId) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean acceptFriend(String loggedInUserId, String friendToAcceptId) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean cancelFriendshipRequest(String loggedInUserId, String friendToRejectId) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<RelationShipDTO> searchUsers(String loggedInUserId, String search) {
		// TODO Auto-generated method stub
		return null;
	}

}
