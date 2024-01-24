package com.artdevs.dto.message;

import java.util.Date;
import java.util.List;

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

    private int status;

    private Date timeRelation;
    
    private String userActionID;
    
    private String UserID1;
    
    private String UserID2;
    
//    private List<String> messageID;
}
