package com.artdevs.dto.post;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReplyCommentToPostDTO {

    private long id;

    private String content;

    private MultipartFile[] listImageOfComment;

    private Date timeComment;

    private String UserID;

    private Long commentID;
}
