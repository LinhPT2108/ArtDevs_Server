package com.artdevs.restcontroller.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artdevs.domain.entities.post.HashTag;
import com.artdevs.dto.post.HashTagDTO;
import com.artdevs.mapper.post.HashTagMapper;
import com.artdevs.repositories.post.HashtagRepository;
import com.artdevs.utils.Path;

@RestController
@RequestMapping(Path.path_api)
public class HashTagRestController {
    @Autowired
    HashtagRepository hashtagRepository;

    @PostMapping("/hashtag")
    public ResponseEntity<HashTag> postHashTag(@RequestBody HashTagDTO hashTagDTO) {
        return ResponseEntity.ok(hashtagRepository.save(HashTagMapper.convertToHashTag(hashTagDTO)));
    }

    @GetMapping("/hashtag")
    public ResponseEntity<List<HashTag>> getHashTag() {
        return ResponseEntity.ok(hashtagRepository.findAll());
    }
}
