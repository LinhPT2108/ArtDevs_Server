package com.artdevs.restcontroller.post;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.artdevs.domain.entities.post.Comment;
import com.artdevs.domain.entities.post.PictureOfComment;
import com.artdevs.domain.entities.post.ReplyComment;
import com.artdevs.domain.entities.user.User;
import com.artdevs.dto.post.CommentToGetDTO;
import com.artdevs.dto.post.CommentToPostDTO;
import com.artdevs.dto.post.ReplyCommentToGetDTO;
import com.artdevs.dto.post.ReplyCommentToPostDTO;
import com.artdevs.mapper.post.CommentMapper;
import com.artdevs.mapper.post.ReplyCommentMapper;
import com.artdevs.services.CommentService;
import com.artdevs.services.ImageOfCommentService;
import com.artdevs.services.PostService;
import com.artdevs.services.ReplyCommentService;
import com.artdevs.services.UserService;
import com.artdevs.utils.Global;

@RestController
@RequestMapping(Global.path_api)
public class CommentRestController {
	@Autowired
	CommentService commentService;
	@Autowired ReplyCommentService repcommentservice;
	@Autowired
	PostService postservice;
	@Autowired
	UserService userservice;
	@Autowired
	ImageOfCommentService imgservice;

	@PostMapping("/comment")
	public ResponseEntity<CommentToGetDTO> postComment(@RequestBody CommentToPostDTO CommentToPostDTO) {
		Authentication authenticate = SecurityContextHolder.getContext().getAuthentication();
		User user = userservice.findByEmail(authenticate.getName());
		CommentToPostDTO.setUserToPost(user.getUserId());
		CommentToPostDTO.setTimeComment(new Date());
		Comment cmt = commentService.saveComment(CommentMapper.convertToEntity(CommentToPostDTO, userservice, postservice));
//		if (CommentToPostDTO.getListImageofComment() != null) {
//			List<PictureOfComment> listimg = new ArrayList<>();
//			MultipartFile[] listImg = CommentToPostDTO.getListImageofComment();
//			for (MultipartFile multipart : listImg) {
//				try {
//					PictureOfComment imgofcmt = imgservice.saveImageOfComment(cmt.getId(), multipart);
//					listimg.add(imgofcmt);
//				} catch (Exception e) {
//					// TODO: handle exception
//					System.out.println(e);
//					e.printStackTrace();
//				}
//			}
//			cmt.setListPictureOfComment(listimg);
//		}
		return ResponseEntity.ok(CommentMapper.convertToCommentToGetDTO(cmt));
	}
	
	@PostMapping("/repcomment/{commentID}")
	public ResponseEntity<ReplyCommentToGetDTO> postComment(@PathVariable("commentID") long commentID,@ModelAttribute ReplyCommentToPostDTO repcommentDTO) {
		Authentication authenticate = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(authenticate.getName());
		User user = userservice.findByEmail(authenticate.getName());
		repcommentDTO.setUserID(user.getUserId());
		repcommentDTO.setTimeComment(new Date());
		repcommentDTO.setCommentID(commentID);
		ReplyComment repcmt = repcommentservice.createReplyComment(ReplyCommentMapper.convertToReplyComment(repcommentDTO, userservice, commentService));
		System.out.println("test:"+repcommentDTO.getListImageOfComment());
		if (repcommentDTO.getListImageOfComment() != null) {
			List<PictureOfComment> listimg = new ArrayList<>();
			MultipartFile[] listImg = repcommentDTO.getListImageOfComment();
			for (MultipartFile multipart : listImg) {
				try {
					PictureOfComment imgofcmt = imgservice.saveImageOfReplyComment(repcmt.getId(), multipart);
					listimg.add(imgofcmt);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e);
					e.printStackTrace();
				}
			}
			repcmt.setListPictureOfComment(listimg);
		}
		return ResponseEntity.ok(ReplyCommentMapper.convertToGetDTO(repcmt));
	}

//	@PostMapping("/comment/{postid}")
//	public ResponseEntity<Comment> postComment(@PathVariable("postid") String postid,
//			@RequestBody CommentToPostDTO CommentToPostDTO) {
//		CommentToPostDTO.setPostID(postid);
//		CommentToPostDTO.setTimeComment(new Date());
//		return ResponseEntity
//				.ok(commentService.saveComment(CommentMapper.convertToEntity(CommentToPostDTO, userservice, postservice)));
//	}

	@GetMapping("/comment")
	public ResponseEntity<List<CommentToGetDTO>> getCommnet() {
		List<CommentToGetDTO> listCommentToGetDTO = new ArrayList<>();
		List<Comment> listComment = commentService.findAll();
		for (Comment comment : listComment) {
			listCommentToGetDTO.add(CommentMapper.convertToCommentToGetDTO(comment));
		}
		return ResponseEntity.ok(listCommentToGetDTO);
	}

	@GetMapping("/comment/{postID}/page/{page}")
	public ResponseEntity<List<CommentToGetDTO>> getCommnetByPostID(@PathVariable("postID") String postid,
			@PathVariable("page") int pagenumber) {
		List<CommentToGetDTO> listCommentToGetDTO = new ArrayList<>();
		Page<Comment> listComment = commentService.findpagecommentbyPostID(postid, pagenumber);
		for (Comment comment : listComment) {
			listCommentToGetDTO.add(CommentMapper.convertToCommentToGetDTO(comment));
		}
		return ResponseEntity.ok(listCommentToGetDTO);
	}

}
