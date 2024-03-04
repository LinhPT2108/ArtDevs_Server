package com.artdevs.dto.message;

import java.util.Date;
import java.util.List;

import com.artdevs.dto.CustomDTO.UserGetRelationDTO;
import com.artdevs.dto.post.UserPostDTO;

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

//    private String relationShipActionUser;
//
//    private String relationShipUserOneId;
//
//    private String relatioShipUserTwoId;

//    private List<MessageDTO> listRelationMessage;

    private UserGetRelationDTO userAction;

    private String UserID1;

    private String UserID2;
//
//    private List<String> messageID;
}
