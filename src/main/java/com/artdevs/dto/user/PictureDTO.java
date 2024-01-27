package com.artdevs.dto.user;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PictureDTO {

    private int id;

    private String cloudinaryPublicId;

    private String description;

    private String imageUrl;

    private String userId;
    
    private boolean positionOfPicture;
    
    private Date time;
}
