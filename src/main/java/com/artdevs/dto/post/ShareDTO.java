package com.artdevs.dto.post;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShareDTO {

    private long id;

    private String typePost;
    
    // private String fullname;

    private UserPostDTO userPostDto;
    
    private String content;

    private PostToGetDTO postId;
    
    private Date timeCreate;
}
