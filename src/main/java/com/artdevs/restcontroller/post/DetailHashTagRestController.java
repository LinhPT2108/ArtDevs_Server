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

import com.artdevs.domain.entities.post.DetailHashtag;
import com.artdevs.dto.post.DetailHashtagDTO;
import com.artdevs.mapper.post.DetailHashTagMapper;
import com.artdevs.repositories.post.DetailHashtagRepository;
import com.artdevs.services.DetailHashTagService;
import com.artdevs.utils.Global;

@RestController
@RequestMapping(Global.path_api)
public class DetailHashTagRestController {
    @Autowired
    DetailHashTagService detailHashTagService;

    @Autowired
    DetailHashtagRepository detailHashtagRepository;

    @PostMapping("/detailhashtag")
    public ResponseEntity<DetailHashtag> postDetailHashTag(@RequestBody DetailHashtagDTO detailHashtagDTO) {
        return ResponseEntity
                .ok(detailHashTagService
                        .saveDetailHashtag(DetailHashTagMapper.convertTodDetailHashtag(detailHashtagDTO)));
    }

    @GetMapping("/detailhashtag")
    public ResponseEntity<List<DetailHashtagDTO>> getDetailHashTag() {
        List<DetailHashtagDTO> listDetailHashtagDTO = new ArrayList<>();
        List<DetailHashtag> listDetailHashtag = detailHashtagRepository.findAll();
        for (DetailHashtag detail : listDetailHashtag) {
            listDetailHashtagDTO.add(DetailHashTagMapper.convertToDetailHashTagDTO(detail));
        }
        // System.out.println(listDetailHashtagDTO.toString());
        return ResponseEntity.ok(listDetailHashtagDTO);
    }
}
