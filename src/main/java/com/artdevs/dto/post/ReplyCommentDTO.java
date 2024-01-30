package com.artdevs.dto.post;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReplyCommentDTO {

    private long id;

    private String content;

    private List<String> listPicture;

    private Date timeComment;

    private String UserID;

    private Long commentID;
}
