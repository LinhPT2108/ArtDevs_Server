package com.artdevs.services;

import java.util.List;

import com.artdevs.domain.entities.post.HashTag;

public interface HashTagService {
    HashTag findHashTagById(Integer hashtagId);

    List<HashTag> findAll();

    HashTag saveHashTag(HashTag hashTag);

    HashTag updateHashTag(HashTag hashTag);

    void deleteHashTag(HashTag hashTag);
}
