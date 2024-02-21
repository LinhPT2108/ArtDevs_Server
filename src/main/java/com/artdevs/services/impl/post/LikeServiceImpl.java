package com.artdevs.services.impl.post;



import static com.artdevs.utils.ReponseMessageConstants.*;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.artdevs.domain.entities.post.Likes;
import com.artdevs.domain.entities.post.Post;
import com.artdevs.domain.entities.user.User;
import com.artdevs.repositories.post.LikesRepository;
import com.artdevs.repositories.post.PostRepository;
import com.artdevs.services.LikesService;
import com.artdevs.services.UserService;
import com.artdevs.utils.CustomException;
@Service
public class LikeServiceImpl implements LikesService {

    @Autowired
    LikesRepository likesRepository;
    
    @Autowired
    PostRepository postrepository;
    
    @Autowired UserService userservice;
    
    @Override
    public Likes findLikesById(Long likeId) {
        Optional<Likes> likesOptional = likesRepository.findById(likeId);
        return likesOptional.orElse(null);
    }

    @Override
    public List<Likes> findAll() {
        return likesRepository.findAll();
    }

    @Override
    public Likes saveLikes(Likes likes) {
        return likesRepository.save(likes);
    }

    @Override
    public Likes updateLikes(Likes likes) {
        return likesRepository.save(likes);
    }

    @Override
    public void deleteLikes(Likes likes) {
        likesRepository.delete(likes);
    }
    
    @Override
    public boolean addLike(String postId) throws Exception {
        Post post = this.postrepository.findById(postId).orElse(null);
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        User user = userservice.findByEmail(auth.getName());


        Likes likeByUserAndPost = this.likesRepository.findLikesByUserIDandPostID(user, post);
        System.out.println(likeByUserAndPost);
        if (likeByUserAndPost == null) {
            Likes like = new Likes();
            like.setUserLikeId(user);
            like.setPostLikeId(post);


            return this.likesRepository.save(like) != null;
        } else {
            throw new CustomException(FAILURE_POST_LIKE_MESSAGE);
        }
    }
    
    @Override
    public boolean unLike(String postId) throws Exception {
        Post post = this.postrepository.findById(postId).orElse(null);
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        User user = userservice.findByEmail(auth.getName());


        Likes likeByUserAndPost = this.likesRepository.findLikesByUserIDandPostID(user, post);
        
        if (likeByUserAndPost != null) {
        	likesRepository.delete(likeByUserAndPost);
            return true;
        } else {
            throw new CustomException(FAILURE_POST_UNLIKE_MESSAGE);
        }
    }

	@Override
	public Likes findByPostAndUserLogged(String postId, User user) {
		return likesRepository.findByPostLikeId_PostIdAndUserLikeId(postId, user);
	}
}
