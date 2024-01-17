package com.artdevs.dto.user;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogDTO {

    private int id;

    private String action;

    private String method;

    private String tableName;

    private Date timeLog;

    private String username;
}
