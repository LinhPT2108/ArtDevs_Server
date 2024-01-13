package com.artdevs.dto;

import java.util.Date;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TransitionInfoDTO {

    private String id;

    private long price_match;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timeTransiton;

    private String userId1;

    private String userId2;

    private int listWallet;
}
