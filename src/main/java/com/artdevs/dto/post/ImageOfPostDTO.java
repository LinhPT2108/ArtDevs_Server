package com.artdevs.dto.post;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageOfPostDTO {

    private int id;

    private String imageOfPostUrl;

    private String postID;
    
    private String cloudinaryPublicId;
    
    private  Date time;
}
