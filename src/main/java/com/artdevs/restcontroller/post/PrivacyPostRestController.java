package com.artdevs.restcontroller.post;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.artdevs.domain.entities.post.Post;
import com.artdevs.domain.entities.post.PrivacyPostDetail;
import com.artdevs.services.PostService;
import com.artdevs.services.PrivacyPostDetailService;
import com.artdevs.services.PrivacyPostService;
import com.artdevs.utils.Global;

@RestController
@RequestMapping(Global.path_api)
@CrossOrigin("*")
public class PrivacyPostRestController {
	@Autowired
	PrivacyPostDetailService privacyPostDetailService;
	@Autowired
	PostService postService;
	@Autowired
	PrivacyPostService privacyPostService;
	
	@PutMapping("/update-privacy-post/{postId}")
	public ResponseEntity<Boolean> putMethodName(@PathVariable("postId") String postId, @RequestParam("namePrivacy") String namePrivacy) {
		try {
			Post post = postService.findPostById(postId);
			List<PrivacyPostDetail> privacyPostDetail = privacyPostDetailService.findByPost(post);
			for (PrivacyPostDetail p : privacyPostDetail) {
				p.setStatus(false);
				privacyPostDetailService.savePrivacyPostDetail(p);
			}
			PrivacyPostDetail privacyPostDetailSave = new PrivacyPostDetail();
			privacyPostDetailSave.setCreateDate(new Date());
			privacyPostDetailSave.setPost(post);
			privacyPostDetailSave.setStatus(true); 
			privacyPostDetailSave.setPrivacyPost(privacyPostService.findByNamePrivacy(namePrivacy));
			privacyPostDetailService.savePrivacyPostDetail(privacyPostDetailSave);
			return ResponseEntity.ok(true);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return ResponseEntity.ok(false);
		}
	}
}
