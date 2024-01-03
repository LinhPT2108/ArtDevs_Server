package com.artdevs.dto.message;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class RelationShipDTO {

    private int id;

    private String status;

    private Date timeRelation;
}
