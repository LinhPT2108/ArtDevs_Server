package com.artdevs.services.impl.post;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.artdevs.domain.entities.post.DetailHashtag;
import com.artdevs.repositories.post.DetailHashtagRepository;
import com.artdevs.services.DetailHashTagService;

@Service
public class DetailHashTagServiceImpl implements DetailHashTagService {

    @Autowired
    DetailHashtagRepository detailHashtagRepository;

    @Override
    public DetailHashtag findDetailHashtagById(Integer detailHashTagId) {
        Optional<DetailHashtag> detailHashTagOptional = detailHashtagRepository.findById(detailHashTagId);
        return detailHashTagOptional.orElse(null);
    }

    @Override
    public List<DetailHashtag> findAll() {
        return detailHashtagRepository.findAll();
    }

    @Override
    public DetailHashtag saveDetailHashtag(DetailHashtag detailHashtag) {
        return detailHashtagRepository.save(detailHashtag);
    }

    @Override
    public DetailHashtag updateDetailHashtag(DetailHashtag detailHashtag) {
        return detailHashtagRepository.save(detailHashtag);
    }

    @Override
    public void deleteDetailHashtag(DetailHashtag detailHashtag) {
        detailHashtagRepository.delete(detailHashtag);
    }

    @Override
    public Optional<Page<DetailHashtag>> findbyKeyword(String keyword, Pageable pageable) {
        // TODO Auto-generated method stub
        return detailHashtagRepository.findByKeyword(keyword, pageable);
    }

    @Override
    public DetailHashtag findDetaiHashTagByName(String detailHashTagText) {

        DetailHashtag result = detailHashtagRepository.findByHashtagText(detailHashTagText).get(0);

        return result;
    }
}
