package com.artdevs.restcontroller.post;

import static com.artdevs.utils.ReponseMessageConstants.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.artdevs.domain.entities.post.Share;
import com.artdevs.dto.post.ShareDTO;
import com.artdevs.mapper.post.ShareMapper;
import com.artdevs.repositories.post.ShareRepository;
import com.artdevs.services.ShareService;
import com.artdevs.utils.Global;

@RestController
@RequestMapping(Global.path_api)
public class ShareRestController {

	@Autowired
	ShareService shareService;

	@Autowired
	ShareRepository shareRepository;

	@PostMapping("/share/{postid}")
	public ResponseEntity<?> addShare(@PathVariable("postid") String postid, @RequestParam("content") String content) {
		try {
			return ResponseEntity.ok(shareService.addShare(postid, content));
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.ok(false);
		}
	}

	@PostMapping("/deleteshare/{postid}")
	public ResponseEntity<?> unShare(@PathVariable("postid") String postid) {
		try {
			return ResponseEntity.ok(shareService.unShare(postid));
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.ok(FAILURE_SAVING_UNSHARE_POST);
		}
	}
}
