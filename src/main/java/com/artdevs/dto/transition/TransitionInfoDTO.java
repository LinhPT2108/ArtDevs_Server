package com.artdevs.dto.transition;

import java.util.Date;

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

    private Date timeTransiton;

    private String userId1;

    private String userId2;

    private int walletId;
}
