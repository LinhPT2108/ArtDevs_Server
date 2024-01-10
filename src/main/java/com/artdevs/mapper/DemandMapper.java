package com.artdevs.mapper;

import org.modelmapper.ModelMapper;

import com.artdevs.domain.entities.user.Demand;
import com.artdevs.dto.DemandDTO;

public class DemandMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static DemandDTO convertToDemandDTO(Demand demand) {
        DemandDTO demandDTO = modelMapper.map(demand, DemandDTO.class);
        demandDTO.setUsername(demand.getUser().getUsername());
        demandDTO.setProgramingLanguage(demand.getLanguage().getLanguageName());
        return demandDTO;
    }

    public static Demand convertToDemand(DemandDTO demandDTO) {
        Demand demand = modelMapper.map(demandDTO, Demand.class);
        return demand;
    }
}
