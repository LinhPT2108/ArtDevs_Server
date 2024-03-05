package com.artdevs.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.artdevs.domain.entities.post.DetailHashtag;

public interface DetailHashTagService {
    DetailHashtag findDetailHashtagById(Integer detailHashTagId);

    List<DetailHashtag> findAll();

    DetailHashtag saveDetailHashtag(DetailHashtag detailHashtag);

    DetailHashtag updateDetailHashtag(DetailHashtag detailHashtag);

    void deleteDetailHashtag(DetailHashtag detailHashtag);
    
    Optional<Page<DetailHashtag>> findbyKeyword(String keyword, Pageable pageable);
    
    Optional<List<DetailHashtag>> findByKeywordNonePage(String keyword);
}
