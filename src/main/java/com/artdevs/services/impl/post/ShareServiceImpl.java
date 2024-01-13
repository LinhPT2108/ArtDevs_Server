package com.artdevs.services.impl.post;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artdevs.domain.entities.post.Share;
import com.artdevs.repositories.post.ShareRepository;
import com.artdevs.services.ShareService;
@Service
public class ShareServiceImpl implements ShareService {

    @Autowired
    ShareRepository shareRepository;

    @Override
    public Share findShareById(Long shareId) {
        Optional<Share> shareOptional = shareRepository.findById(shareId);
        return shareOptional.orElse(null);
    }

    @Override
    public List<Share> findAll() {
        return shareRepository.findAll();
    }

    @Override
    public Share saveShare(Share share) {
        return shareRepository.save(share);
    }

    @Override
    public Share updateShare(Share share) {
        return shareRepository.save(share);
    }

    @Override
    public void deleteShare(Share share) {
        shareRepository.delete(share);
    }

}
