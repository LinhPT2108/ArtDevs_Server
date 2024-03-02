package com.artdevs.dto.post;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommentToPostDTO {

//    private long id;

    private String content;

//    private MultipartFile[] listImageofComment;

    private Date timeComment;

    private String userToPost;

    private String postToPost;

}
