package com.artdevs.dto.post;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentToPostDTO {

    private long id;

    private String content;

    private MultipartFile[] listImageofComment;

    private long Count;

    private Date timeComment;

    private String UserID;

    private String PostID;

}
