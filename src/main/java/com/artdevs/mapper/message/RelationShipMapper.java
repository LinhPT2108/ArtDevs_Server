package com.artdevs.mapper.message;

import org.modelmapper.ModelMapper;

import com.artdevs.domain.entities.message.RelationShip;
import com.artdevs.dto.message.RelationShipDTO;

public class RelationShipMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static RelationShipDTO convertToRelationShipDTO(RelationShip relationShip) {
        RelationShipDTO relationShipDTO = modelMapper.map(relationShip, RelationShipDTO.class);
        return relationShipDTO;
    }

    public static RelationShip convertToRelationShip(RelationShipDTO relationShipDTO) {
        RelationShip relationShip = modelMapper.map(relationShipDTO, RelationShip.class);
        return relationShip;
    }
}
