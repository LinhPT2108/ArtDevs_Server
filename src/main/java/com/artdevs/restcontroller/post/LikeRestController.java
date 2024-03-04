package com.artdevs.restcontroller.post;

import static com.artdevs.utils.ReponseMessageConstants.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artdevs.domain.entities.post.Likes;
import com.artdevs.domain.entities.post.Post;
import com.artdevs.domain.entities.user.Notification;
import com.artdevs.domain.entities.user.User;
import com.artdevs.dto.post.LikeDTO;
import com.artdevs.mapper.post.LikeMapper;
import com.artdevs.repositories.post.LikesRepository;
import com.artdevs.services.LikesService;
import com.artdevs.services.NotificationService;
import com.artdevs.services.PostService;
import com.artdevs.services.UserService;
import com.artdevs.utils.Global;

@RestController
@RequestMapping(Global.path_api)
public class LikeRestController {
	@Autowired
	LikesService likeService;

	@Autowired
	LikesRepository likesRepository;

	@Autowired
	NotificationService notificationService;

	@Autowired
	UserService userService;

	@Autowired
	PostService postService;

	@PostMapping("/like/{postID}")
	public ResponseEntity<?> addLikes(@PathVariable("postID") String postID) {
		try {
			Post post = this.postService.findPostById(postID);
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();

			User user = userService.findByEmail(auth.getName());
			boolean responseLike = likeService.addLike(post, user);
			if (responseLike) {
				String fullname = String.join(" ", Global.safeTrim(user.getFirstName()),
						Global.safeTrim(user.getMiddleName()), Global.safeTrim(user.getLastName()));
				Notification notificationSave = new Notification();
				notificationSave.setCreateDate(new Date());
				notificationSave.setMessage(fullname + SUCCESSFUL_LIKE_POST_MESSAGE + "bạn");
				notificationSave.setSender(user);
				notificationSave.setReceiver(post.getUser());
				notificationSave.setRead(false);
				notificationSave.setType("post");
				notificationSave.setPostId(postID);
				notificationService.createNotification(notificationSave);
			}
			return ResponseEntity.ok(responseLike);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.ok(FAILURE_POST_LIKE_MESSAGE);
		}
	}

	@PostMapping("/unlike/{postID}")
	public ResponseEntity<?> UnLikes(@PathVariable("postID") String postID) {
		try {

			return ResponseEntity.ok(likeService.unLike(postID));
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.ok(FAILURE_POST_UNLIKE_MESSAGE);
		}
	}
}
