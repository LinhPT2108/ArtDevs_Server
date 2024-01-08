package com.artdevs.mapper;

import org.modelmapper.ModelMapper;

import com.artdevs.domain.entities.user.TransitionInfo;
import com.artdevs.dto.TransitionInfoDTO;

public class TransitionInfoMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static TransitionInfoDTO convertToTransitionInfoDTO(TransitionInfo transitionInfo) {
        TransitionInfoDTO transitionInfoDTO = modelMapper.map(transitionInfo, TransitionInfoDTO.class);
        return transitionInfoDTO;
    }

    public static TransitionInfo convertToTransitionInfo(TransitionInfoDTO transitionInfoDTO) {
        TransitionInfo transitionInfo = modelMapper.map(transitionInfoDTO, TransitionInfo.class);
        return transitionInfo;
    }
}
