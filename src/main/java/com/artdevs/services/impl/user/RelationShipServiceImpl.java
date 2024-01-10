package com.artdevs.services.impl.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artdevs.domain.entities.message.RelationShip;
import com.artdevs.repositories.message.RelationshipRepository;
import com.artdevs.services.RelationshipService;
@Service
public class RelationShipServiceImpl implements RelationshipService {

    @Autowired
    RelationshipRepository relationshipRepository;

    @Override
    public RelationShip findRelationShipById(Integer relationshipId) {
        Optional<RelationShip> relationShipOptional = relationshipRepository.findById(relationshipId);
        return relationShipOptional.orElse(null);
    }

    @Override
    public List<RelationShip> findAll() {
        return relationshipRepository.findAll();
    }

    @Override
    public RelationShip saveRelationShip(RelationShip relationShip) {
        return relationshipRepository.save(relationShip);
    }

    @Override
    public RelationShip updateRelationShip(RelationShip relationShip) {
        return relationshipRepository.save(relationShip);
    }

    @Override
    public void deleteRelationShip(RelationShip relationShip) {
        relationshipRepository.delete(relationShip);
    }

}
