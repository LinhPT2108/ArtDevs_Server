package com.artdevs.dto;

import java.util.List;

import com.artdevs.domain.entities.user.MethodPay;
import com.artdevs.domain.entities.user.TransitionInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class WalletDTO {

    private int id;

    private long surplus;

    private List<TransitionInfo> listTransitionInfo;

    private List<MethodPay> listMethodPay;
}
