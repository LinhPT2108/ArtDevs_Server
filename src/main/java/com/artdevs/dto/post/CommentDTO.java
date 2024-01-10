package com.artdevs.dto.post;

import java.util.Date;
import java.util.List;

import com.artdevs.domain.entities.post.Post;
import com.artdevs.domain.entities.user.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CommentDTO {

    private long id;

    private String content;

    private String imageUrl;

    private long Count;

    private Date timeComment;
    
    private String UserID;
    
    private String PostID;
}
