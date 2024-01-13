package com.artdevs.dto.post;

import java.util.Date;
import java.util.List;

import com.artdevs.domain.entities.post.Post;
import com.artdevs.domain.entities.user.User;

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

public class CommentDTO {

    private long id;

    @Nationalized
    private String content;

    private String imageUrl;

    private long Count;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timeComment;
    
    private String UserID;
    
    private String PostID;


    @Temporal(TemporalType.TIMESTAMP)
    private Date timeUserId;


}
