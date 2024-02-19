package com.artdevs.mapper;

import org.modelmapper.ModelMapper;

import com.artdevs.domain.entities.user.Skill;
import com.artdevs.domain.entities.user.Star;
import com.artdevs.dto.user.SkillDTO;
import com.artdevs.dto.user.StarDTO;

public class StarMapper {

    private static final ModelMapper modelMapper = new ModelMapper();
    
    public static StarDTO convertToStarDTO(Star star) {
    	StarDTO starDTO = modelMapper.map(star, StarDTO.class);
    	starDTO.setUserReceiveId(star.getUserReceive().getUserId());
    	starDTO.setUserSendId(star.getUserSend().getUserId());
        return starDTO;
    }

    public static Star convertToStar(StarDTO starDTO) {
    	Star star = modelMapper.map(starDTO, Star.class);
        return star;
    }
}
