package com.artdevs.services;

import java.util.List;

import com.artdevs.domain.entities.post.Share;

public interface ShareService {
    Share findShareById(Long shareId);

    List<Share> findAll();

    Share saveShare(Share share);

    Share updateShare(Share share);

    void deleteShare(Share share);
}
