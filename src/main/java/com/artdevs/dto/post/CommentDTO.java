package com.artdevs.dto.post;

import java.util.Date;

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

    private String content;

    private String imageUrl;

    private long Count;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timeComment;
}
