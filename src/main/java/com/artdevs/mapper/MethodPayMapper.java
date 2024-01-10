package com.artdevs.mapper;

import org.modelmapper.ModelMapper;

import com.artdevs.domain.entities.user.MethodPay;
import com.artdevs.dto.transition.MethodPayDTO;

public class MethodPayMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static MethodPayDTO convertToMethodPayDTO(MethodPay methodPay) {
        MethodPayDTO methodPayDTO = modelMapper.map(methodPay, MethodPayDTO.class);
        return methodPayDTO;
    }

    public static MethodPay convertToMethodPay(MethodPayDTO methodPayDTO) {
        MethodPay methodPay = modelMapper.map(methodPayDTO, MethodPay.class);
        return methodPay;
    }
}
