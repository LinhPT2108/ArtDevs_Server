package com.artdevs.restcontroller.post;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artdevs.domain.entities.post.PrivacyPost;
import com.artdevs.dto.post.privacyPostDTO;
import com.artdevs.mapper.post.PrivacyPostMapper;
import com.artdevs.repositories.post.PrivacyPostResponsitory;
import com.artdevs.utils.Global;

@RestController
@RequestMapping(Global.path_api)
public class PrivacyPostRestCotroller {

    @Autowired
    PrivacyPostResponsitory privacyPostResponsitory;

    @GetMapping("/privacyPost")
    public ResponseEntity<List<privacyPostDTO>> getWallet() {
        List<privacyPostDTO> listPrivacyPostDTO = new ArrayList<>();
        List<PrivacyPost> listPrivacyPost = privacyPostResponsitory.findAll();
        for (PrivacyPost privacyPost : listPrivacyPost) {
            listPrivacyPostDTO.add(PrivacyPostMapper.convertToPrivacyPostDTO(privacyPost));
        }
        return ResponseEntity.ok(listPrivacyPostDTO);
    }
}
