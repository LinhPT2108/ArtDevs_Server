package com.artdevs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

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
}
