package com.artdevs.dto.post;

import java.sql.Timestamp;

import org.hibernate.annotations.Nationalized;

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

    private Timestamp timeComment;
}
