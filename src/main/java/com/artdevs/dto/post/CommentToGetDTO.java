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
public class CommentToGetDTO {
	 private long id;

	    private String content;

	    private List<String> listImageofComment;

	    private Date timeComment;

	    private UserPostDTO UserID;
	    
	    private UserPostDTO userReceiveDto;
	    
	    private List<ReplyCommentToGetDTO> listReplyComment;
}
