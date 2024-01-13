package com.artdevs.dto.user;

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
public class LogDTO {

    private int id;

    private String action;

    private String method;

    private String tableName;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timeLog;

    private String username;
}
