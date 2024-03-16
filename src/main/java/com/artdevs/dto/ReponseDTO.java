package com.artdevs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReponseDTO<T> {
    private int statusCode;
    private String message;
    private T model;

}