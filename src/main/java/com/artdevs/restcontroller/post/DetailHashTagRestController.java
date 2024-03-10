package com.artdevs.restcontroller.post;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.artdevs.domain.entities.post.DetailHashtag;
import com.artdevs.dto.post.DetailHashtagDTO;
import com.artdevs.dto.post.DetailHashtagToGetDTO;
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
    
    @GetMapping("/search-detailhashtag")
    public ResponseEntity<List<DetailHashtagToGetDTO>> getDetailHashTagByKeyword(@RequestParam("keyword") String keyword) {
        List<DetailHashtagToGetDTO> listDetailHashtagDTO = new ArrayList<>();
        System.out.println(keyword);
        Optional<List<DetailHashtag>> listDetailHashtag = detailHashtagRepository.findByKeywordNonPage(keyword);
        if(listDetailHashtag.isPresent()) {
        	System.out.println(listDetailHashtag.get().size());
        	for (DetailHashtag detail : listDetailHashtag.get()) {
        		listDetailHashtagDTO.add(DetailHashTagMapper.convertToDetailHashTagToGetDTO(detail));
        	}
        	// System.out.println(listDetailHashtagDTO.toString());
        	return ResponseEntity.ok(listDetailHashtagDTO);        	
        }else {
        	return ResponseEntity.ok(null);
        }
    }
    
}
