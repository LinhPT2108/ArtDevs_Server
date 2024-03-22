package com.artdevs.restcontroller.post;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.artdevs.domain.entities.post.Comment;
import com.artdevs.domain.entities.post.PictureOfComment;
import com.artdevs.domain.entities.post.Post;
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

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(Global.path_api)
public class CommentRestController {
	@Autowired
	CommentService commentService;
	@Autowired
	ReplyCommentService repcommentservice;
	@Autowired
	PostService postservice;
	@Autowired
	UserService userservice;
	@Autowired
	ImageOfCommentService imgservice;

	@PostMapping(value = "/comment")
	public ResponseEntity<CommentToGetDTO> postComment(
			@RequestPart("commentToPostDTO") CommentToPostDTO commentToPostDTO,
			@RequestPart("listImageofComment") Optional<List<MultipartFile>> listImageofComment) {
//		System.out.println(listImageofComment);
		Authentication authenticate = SecurityContextHolder.getContext().getAuthentication();
		if (authenticate.getName().equals("anonymousUser")) {
			return ResponseEntity.status(HttpStatus.SC_UNAUTHORIZED).build();
		}
		
		System.out.println(commentToPostDTO.toString());
		User userSend = userservice.findByEmail(authenticate.getName());
		User userReceive = userservice.findUserById(commentToPostDTO.getUserReceive());
		Post post = postservice.findPostById(commentToPostDTO.getPostToPost());
		Comment cmtSave = new Comment();
		cmtSave.setTimeComment(new Date());
		cmtSave.setContent(commentToPostDTO.getContent());
		cmtSave.setUserReportId(userSend);
		cmtSave.setUserReceive(userReceive);
		cmtSave.setPostCommentId(post);
//		CommentToPostDTO.setUserToPost(user.getUserId());
		Comment cmt = commentService.saveComment(cmtSave);
		if (listImageofComment.isPresent()) {
			List<PictureOfComment> listimg = new ArrayList<>();
			List<MultipartFile> listImgNew = listImageofComment.get();
			for (MultipartFile multipart : listImgNew) {
				try {
					PictureOfComment imgofcmt = imgservice.saveImageOfComment(cmt.getId(), multipart);
					listimg.add(imgofcmt);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(">82: " + e);
					e.printStackTrace();
				}
			}
			cmt.setListPictureOfComment(listimg);
		}
		return ResponseEntity.ok(CommentMapper.convertToCommentToGetDTO(cmt));
	}

	@PostMapping("/repcomment/{commentID}")
	public ResponseEntity<ReplyCommentToGetDTO> postComment(@PathVariable("commentID") long commentID,
			@RequestPart ReplyCommentToPostDTO repcommentDTO,
			@RequestPart("listImageofComment") Optional<List<MultipartFile>> listImageofComment) {
		Authentication authenticate = SecurityContextHolder.getContext().getAuthentication();
		if (authenticate.getName().equals("anonymousUser")) {
			return ResponseEntity.status(HttpStatus.SC_UNAUTHORIZED).build();
		}
		System.out.println(authenticate.getName());

		User userSend = userservice.findByEmail(authenticate.getName());
		User userReceive = userservice.findUserById(repcommentDTO.getUserReceive());
		repcommentDTO.setUserToPost(userSend.getUserId());
		repcommentDTO.setCommentToPost(commentID);
		repcommentDTO.setUserReceive(userReceive.getUserId());
		ReplyComment repcmt = repcommentservice.createReplyComment(
				ReplyCommentMapper.convertToReplyComment(repcommentDTO, userservice, commentService));

		System.out.println(repcommentDTO.toString());

		if (listImageofComment.isPresent()) {
			List<PictureOfComment> listimgReturn = new ArrayList<>();
			List<MultipartFile> listImg = listImageofComment.get();
			for (MultipartFile multipart : listImg) {
				try {
					PictureOfComment imgofcmt = imgservice.saveImageOfReplyComment(repcmt.getId(), multipart);
					listimgReturn.add(imgofcmt);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e);
				}
			}
			repcmt.setListPictureOfComment(listimgReturn);
		}
		return ResponseEntity.ok(ReplyCommentMapper.convertToGetDTO(repcmt));
	}

	@GetMapping("/comment")
	public ResponseEntity<List<CommentToGetDTO>> getCommnet() {
		List<CommentToGetDTO> listCommentToGetDTO = new ArrayList<>();
		List<Comment> listComment = commentService.findAll();
		for (Comment comment : listComment) {
			listCommentToGetDTO.add(CommentMapper.convertToCommentToGetDTO(comment));
		}
		return ResponseEntity.ok(listCommentToGetDTO);
	}

	@GetMapping("/comment/{postID}")
	public ResponseEntity<List<CommentToGetDTO>> getCommnetByPostID(@PathVariable("postID") String postid,
			@RequestParam("page") Optional<Integer> pagenumber) {
		List<CommentToGetDTO> listCommentToGetDTO = new ArrayList<>();
		Page<Comment> listComment = commentService.findpagecommentbyPostID(postid, pagenumber.orElse(0));
		for (Comment comment : listComment) {
			listCommentToGetDTO.add(CommentMapper.convertToCommentToGetDTO(comment));
		}
		return ResponseEntity.ok(listCommentToGetDTO);
	}

	@DeleteMapping("/comment/{commentId}")
	public ResponseEntity<Boolean> deleteComment(@PathVariable("commentId") long commentId) {
		Authentication authenticate = SecurityContextHolder.getContext().getAuthentication();
		if (authenticate.getName().equals("anonymousUser")) {
			return ResponseEntity.status(HttpStatus.SC_UNAUTHORIZED).build();
		}

		try {
			Comment delComment = commentService.findCommentById(commentId);

			if (!delComment.getListReplyCommentPost().isEmpty()) {
				for (ReplyComment rl : delComment.getListReplyCommentPost()) {
					repcommentservice.deleteReplyComment(rl);
				}
			}
			commentService.deleteComment(delComment);
			return ResponseEntity.ok(true);
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.ok(false);
		}
	}

	@DeleteMapping("/replyComment/{replyCommentId}")
	public ResponseEntity<Boolean> deleteReplyComment(@PathVariable("replyCommentId") long replyCommentId) {
		System.out.println(replyCommentId);
		Authentication authenticate = SecurityContextHolder.getContext().getAuthentication();
		if (authenticate.getName().equals("anonymousUser")) {
			return ResponseEntity.status(HttpStatus.SC_UNAUTHORIZED).build();
		}

		try {
			ReplyComment delReplyComment = repcommentservice.findByReplyCommentID(replyCommentId);
			repcommentservice.deleteReplyComment(delReplyComment);
			return ResponseEntity.ok(true);
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.ok(false);
		}
	}

	@PutMapping("/comment/{commentId}")
	public ResponseEntity<?> updateComment(@PathVariable("commentId") long commentId, @RequestPart String content,
			@RequestPart("listImageofComment") Optional<List<MultipartFile>> listImageofComment) {
		Authentication authenticate = SecurityContextHolder.getContext().getAuthentication();
		if (authenticate.getName().equals("anonymousUser")) {
			return ResponseEntity.status(HttpStatus.SC_UNAUTHORIZED).build();
		}
		try {
			System.out.println(content);
			Comment dataCommentUpdate = commentService.findCommentById(commentId);
			dataCommentUpdate.setId(commentId);
			dataCommentUpdate.setContent(content);
			System.out.println(dataCommentUpdate.toString());
			Comment commentReturn = commentService.saveComment(dataCommentUpdate);
			if (listImageofComment.isPresent()) {
				List<PictureOfComment> listimgReturn = new ArrayList<>();
				List<MultipartFile> listImg = listImageofComment.get();
				if (!commentReturn.getListPictureOfComment().isEmpty()) {
					for (PictureOfComment imgCmtPresent : commentReturn.getListPictureOfComment()) {
						boolean isImgExist = false;
						for (MultipartFile pic : listImg) {
							if (imgCmtPresent.getImageUrl().equals(pic.getOriginalFilename())) {
								isImgExist = true;
								break;
							}
						}
						System.out.println(isImgExist);
						if (!isImgExist) {
							try {
								imgservice.deleteImageOfComment(imgCmtPresent);
							} catch (Exception e) {
								System.out.println(e);
								return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).build();
							}
						}
					}
				}

				for (MultipartFile p : listImg) {
					System.out.println(p.getOriginalFilename());
					PictureOfComment checkImgAlive = imgservice.findImageUrl(p.getOriginalFilename());
					if (checkImgAlive == null) {
						PictureOfComment imgofcmt = imgservice.saveImageOfComment(commentReturn.getId(), p);
						listimgReturn.add(imgofcmt);
					} else {
						listimgReturn.add(checkImgAlive);
					}
				}

				commentReturn.setListPictureOfComment(listimgReturn);

			}else {
				try {
					imgservice.deleteImgOfCmtByCommentId(commentReturn);
					commentReturn.setListPictureOfComment(Collections.emptyList());
				} catch (Exception e) {
					System.out.println(e);
					return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).build();
				}
			}
			return ResponseEntity.ok(CommentMapper.convertToCommentToGetDTO(commentReturn));
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).build();
		}
	}
	
	@PutMapping("/replyComment/{replyCommentId}")
	public ResponseEntity<?> updateReplyComment(@PathVariable("replyCommentId") long replyCommentId, @RequestPart String content,
			@RequestPart("listImageofComment") Optional<List<MultipartFile>> listImageofComment) {
		Authentication authenticate = SecurityContextHolder.getContext().getAuthentication();
		if (authenticate.getName().equals("anonymousUser")) {
			return ResponseEntity.status(HttpStatus.SC_UNAUTHORIZED).build();
		}
		try {
			System.out.println(content);
			ReplyComment dataCommentUpdate = repcommentservice.findByReplyCommentID(replyCommentId);
			dataCommentUpdate.setId(replyCommentId);
			dataCommentUpdate.setContent(content);
			System.out.println(dataCommentUpdate.toString());
			ReplyComment replyCommentReturn = repcommentservice.createReplyComment(dataCommentUpdate);
			if (listImageofComment.isPresent()) {
				List<PictureOfComment> listimgReturn = new ArrayList<>();
				List<MultipartFile> listImg = listImageofComment.get();
				if (!replyCommentReturn.getListPictureOfComment().isEmpty()) {
					for (PictureOfComment imgCmtPresent : replyCommentReturn.getListPictureOfComment()) {
						boolean isImgExist = false;
						for (MultipartFile pic : listImg) {
							if (imgCmtPresent.getImageUrl().equals(pic.getOriginalFilename())) {
								isImgExist = true;
								break;
							}
						}
						System.out.println(isImgExist);
						if (!isImgExist) {
							try {
								imgservice.deleteImageOfComment(imgCmtPresent);
							} catch (Exception e) {
								System.out.println(e);
								return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).build();
							}
						}
					}
				}

				for (MultipartFile p : listImg) {
					System.out.println(p.getOriginalFilename());
					PictureOfComment checkImgAlive = imgservice.findImageUrl(p.getOriginalFilename());
					System.out.println(checkImgAlive);
					if (checkImgAlive == null) {
						System.out.println(replyCommentReturn.getId());
						PictureOfComment imgofcmt = imgservice.saveImageOfReplyComment(replyCommentReturn.getId(), p);
						listimgReturn.add(imgofcmt);
					} else {
						listimgReturn.add(checkImgAlive);
					}
				}

				replyCommentReturn.setListPictureOfComment(listimgReturn);

			}else {
				System.out.println("delete when empty img");
				try {
					imgservice.deleteImgOfReplyCmtByCommentId(replyCommentReturn);
					replyCommentReturn.setListPictureOfComment(Collections.emptyList());
				} catch (Exception e) {
					System.out.println(e);
					return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).build();
				}
			}
			return ResponseEntity.ok(ReplyCommentMapper.convertToGetDTO(replyCommentReturn));
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).build();
		}
	}
}
