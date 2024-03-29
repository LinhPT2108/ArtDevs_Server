package com.artdevs.dto.transition;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MethodPayDTO {

    private int id;


    private String payName;

    private int walletId;

    private String username;
}
