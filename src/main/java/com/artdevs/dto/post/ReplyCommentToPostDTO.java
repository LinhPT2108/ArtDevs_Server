package com.artdevs.dto.post;

import java.util.Date;
import java.util.List;

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
public class ReplyCommentToPostDTO {

    private String content;

//    private MultipartFile[] listImageOfComment;

    private String userToPost;

    private String userReceive;
    
    private Long commentToPost;
}
