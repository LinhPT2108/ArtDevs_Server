package com.artdevs.dto;

import java.util.Date;

import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TransitionInfoDTO {
    
    private String id;

    private long price_match;

    private Date timeTransiton;
}
