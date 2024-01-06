package com.artdevs.services;

import java.util.List;

import com.artdevs.domain.entities.message.RelationShip;

public interface RelationshipService {
    RelationShip findRelationShipById(Integer relationshipId);

    List<RelationShip> findAll();

    RelationShip saveRelationShip(RelationShip relationShip);

    RelationShip updateRelationShip(RelationShip relationShip);

    void deleteRelationShip(RelationShip relationShip);
}
