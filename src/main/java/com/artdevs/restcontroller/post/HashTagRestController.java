package com.artdevs.restcontroller.post;

import java.util.ArrayList;
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
import com.artdevs.services.impl.post.HashTagServiceImpl;
import com.artdevs.utils.Path;

@RestController
@RequestMapping(Path.path_api)
public class HashTagRestController {
    @Autowired
    HashTagServiceImpl hashTagServiceImpl;

    @Autowired
    HashtagRepository hashtagRepository;

    @PostMapping("/hashtag")
    public ResponseEntity<HashTag> postHashTag(@RequestBody HashTagDTO hashTagDTO) {
        return ResponseEntity.ok(hashTagServiceImpl.saveHashTag(HashTagMapper.convertToHashTag(hashTagDTO)));
    }

    @GetMapping("/hashtag")
    public ResponseEntity<List<HashTagDTO>> getHashTag() {
        List<HashTagDTO> listHashTagDTO = new ArrayList<>();
        List<HashTag> lisHashTag = hashtagRepository.findAll();
        for (HashTag hashTag : lisHashTag) {
            listHashTagDTO.add(HashTagMapper.convertToHashTagDTO(hashTag));
        }
        return ResponseEntity.ok(listHashTagDTO);
    }
}
