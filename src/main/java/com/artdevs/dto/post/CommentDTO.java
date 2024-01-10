package com.artdevs.dto.post;

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

public class CommentDTO {

    private long id;

    @Nationalized
    private String content;

    private String imageUrl;

    private long Count;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timeComment;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timeUserId;

    private String commentPostId;

    private String username;
}
