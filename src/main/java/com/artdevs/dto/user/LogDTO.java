package com.artdevs.dto.user;

import com.google.protobuf.Timestamp;

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

    private Timestamp timeLog;
}
