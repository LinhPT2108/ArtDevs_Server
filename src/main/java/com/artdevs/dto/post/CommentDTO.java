package com.artdevs.dto.post;

import java.sql.Timestamp;

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

    private Timestamp timeComment;
}
