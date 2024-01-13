package com.artdevs.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.artdevs.domain.entities.post.TypePost;
import com.artdevs.repositories.post.TypepostRepository;
import com.artdevs.services.TypePostService;

public class TypePostServiceImpl implements TypePostService {

    @Autowired
    TypepostRepository typepostRepository;

    @Override
    public TypePost findTypePostById(Integer typepostId) {
        Optional<TypePost> typePostOptional = typepostRepository.findById(typepostId);
        return typePostOptional.orElse(null);
    }

    @Override
    public List<TypePost> findAll() {
        return typepostRepository.findAll();
    }

    @Override
    public TypePost saveTypePost(TypePost typepost) {
        return typepostRepository.save(typepost);
    }

    @Override
    public TypePost updateTypePost(TypePost typepost) {
        return typepostRepository.save(typepost);
    }

    @Override
    public void deleteTypePost(TypePost typepost) {
        typepostRepository.delete(typepost);
    }

}
