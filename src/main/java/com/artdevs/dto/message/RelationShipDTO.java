package com.artdevs.dto.message;

import java.util.Date;

import org.hibernate.annotations.Nationalized;

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

public class RelationShipDTO {

    private int id;

    @Nationalized
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timeRelation;
}
