package com.artdevs.services;

import java.util.List;

import com.artdevs.domain.entities.post.TypePost;

public interface TypePostService {
    TypePost findTypePostById(Integer typepostId);

    List<TypePost> findAll();

    TypePost saveTypePost(TypePost typepost);

    TypePost updateTypePost(TypePost typepost);

    void deleteTypePost(TypePost typepost);
}
