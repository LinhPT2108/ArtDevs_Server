package com.artdevs.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.artdevs.domain.entities.user.Demand;
import com.artdevs.dto.user.DemandDTO;
import com.artdevs.services.UserService;

public class DemandMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    UserService userService;

    public static DemandDTO convertToDemandDTO(Demand demand) {

        DemandDTO demandDTO = modelMapper.map(demand, DemandDTO.class);
        demandDTO.setUsername(demand.getUser().getUsername());
        return demandDTO;
    }

    public static Demand convertToDemand(DemandDTO demandDTO) {
        Demand demand = modelMapper.map(demandDTO, Demand.class);
        return demand;
    }
}
