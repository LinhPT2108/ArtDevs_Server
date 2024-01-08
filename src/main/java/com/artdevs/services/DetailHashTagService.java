package com.artdevs.services;

import java.util.List;

import com.artdevs.domain.entities.post.DetailHashtag;

public interface DetailHashTagService {
    DetailHashtag findDetailHashtagById(Integer detailHashTagId);

    List<DetailHashtag> findAll();

    DetailHashtag saveDetailHashtag(DetailHashtag detailHashtag);

    DetailHashtag updateDetailHashtag(DetailHashtag detailHashtag);

    void deleteDetailHashtag(DetailHashtag detailHashtag);
}
