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
public class ImageOfCommentDTO {
    private int id;

    private String imageOfCommentUrl;
    
    private long commentID;
    
    private long RepCommentID;
    
}
