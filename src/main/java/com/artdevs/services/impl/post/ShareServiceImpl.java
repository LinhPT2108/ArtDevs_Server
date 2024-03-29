package com.artdevs.services.impl.post;

import static com.artdevs.utils.ReponseMessageConstants.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.artdevs.domain.entities.post.Post;
import com.artdevs.domain.entities.post.Share;
import com.artdevs.domain.entities.user.User;
import com.artdevs.repositories.post.ShareRepository;
import com.artdevs.services.PostService;
import com.artdevs.services.ShareService;
import com.artdevs.services.UserService;
import com.artdevs.utils.CustomException;
import com.artdevs.utils.Global;

@Service
public class ShareServiceImpl implements ShareService {

	@Autowired
	ShareRepository shareRepository;

	@Autowired
	UserService userservice;

	@Autowired
	PostService postservice;

	@Override
	public Share findShareById(String shareId) {
		Optional<Share> shareOptional = shareRepository.findById(shareId);
		return shareOptional.orElse(null);
	}

	@Override
	public List<Share> findAll() {
		return shareRepository.findAll();
	}

	@Override
	public Share addShare(String postId, String content) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userservice.findByEmail(auth.getName());

		Post post = postservice.findPostById(postId);

		Share share = new Share();
		share.setId(Global.generateRandomCode());
		share.setPostShareId(post);
		share.setUserShareId(user);
		share.setContent(content);
		share.setTimeCreate(new Date());
		return shareRepository.save(share);
	}

	@Override
	public boolean unShare(String postId) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userservice.findByEmail(auth.getName());

		Post post = postservice.findPostById(postId);

		Share sharebyUserandPost = shareRepository.findShareByUserIDandPostID(user, post);

		if (sharebyUserandPost != null) {
			shareRepository.delete(sharebyUserandPost);
			return true;
		} else {
			throw new CustomException(FAILURE_SAVING_UNSHARE_POST);
		}
	}

	@Override
	public Optional<List<Share>> findByUser(User user) {
		// TODO Auto-generated method stub
		return shareRepository.findByUserShareId(user);
	}

}
