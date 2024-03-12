package com.artdevs.services;

import java.util.List;
import java.util.Optional;

import com.artdevs.domain.entities.post.DetailHashtag;
import com.artdevs.domain.entities.post.HashTag;
import com.artdevs.domain.entities.post.Post;

public interface HashTagService {
    HashTag findHashTagById(Integer hashtagId);

    List<HashTag> findAll();

    HashTag saveHashTag(HashTag hashTag);

    HashTag updateHashTag(HashTag hashTag);

    void deleteHashTag(HashTag hashTag);
    
    void deleteHashTagByPost(Post post);
    
    Optional<List<HashTag>> findbydetailHashtagAndPost(DetailHashtag detailHashtag,Post post);
}
