package com.artdevs.restcontroller.post;

import static com.artdevs.utils.ReponseMessageConstants.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artdevs.domain.entities.post.Likes;
import com.artdevs.dto.post.LikeDTO;
import com.artdevs.mapper.post.LikeMapper;
import com.artdevs.repositories.post.LikesRepository;
import com.artdevs.services.LikesService;
import com.artdevs.utils.Global;

@RestController
@RequestMapping(Global.path_api)
public class LikeRestController {
    @Autowired
    LikesService likeService;

    @Autowired
    LikesRepository likesRepository;

    @PostMapping("/like/{postID}")
    public ResponseEntity<?> addLikes(@PathVariable("postID") String postID) {
        try {
        	
			return ResponseEntity.ok(likeService.addLike(postID));
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
