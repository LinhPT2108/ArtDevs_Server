package com.artdevs.dto.transition;

import java.util.List;

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

    private List<TransitionInfoDTO> listTransitionInfo;

    private List<MethodPayDTO> listMethodPay;
}
