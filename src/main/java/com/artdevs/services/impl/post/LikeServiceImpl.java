package com.artdevs.services.impl.post;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artdevs.domain.entities.post.Likes;
import com.artdevs.repositories.post.LikesRepository;
import com.artdevs.services.LikesService;
@Service
public class LikeServiceImpl implements LikesService {

    @Autowired
    LikesRepository likesRepository;

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

}
