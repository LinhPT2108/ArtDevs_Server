package com.artdevs.restcontroller.post;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artdevs.domain.entities.post.PrivacyPostDetail;
import com.artdevs.dto.post.PrivacyPostDetailDTO;
import com.artdevs.mapper.post.PrivacyPostDetailMapper;
import com.artdevs.repositories.post.PrivacyPostDetailRespository;
import com.artdevs.utils.Path;

@RestController
@RequestMapping(Path.path_api)
public class PrivacyPostDetailRestCotroller {

    // @Autowired
    // PrivacyPostDetailServiceImpl privacyPostDetailServiceImpl;

    @Autowired
    PrivacyPostDetailRespository priRespository;

    // @PostMapping("/privacyPostDetails")
    // public ResponseEntity<PrivacyPostDetail> postPrivacyPostDetail(
    // @RequestBody PrivacyPostDetailDTO privacyPostDetailDTO) {

    // return ResponseEntity.ok(
    // privacyPostDetailServiceImpl
    // .savePrivacyPostDetail(
    // PrivacyPostDetailMapper.convertToPrivacyPostDetail(privacyPostDetailDTO)));
    // }

    @GetMapping("/privacyPostDetails")
    public ResponseEntity<List<PrivacyPostDetailDTO>> getWallet() {
        List<PrivacyPostDetailDTO> listDTO = new ArrayList<>();
        List<PrivacyPostDetail> list = priRespository.findAll();
        for (PrivacyPostDetail privacyPostDetail : list) {
            listDTO.add(PrivacyPostDetailMapper.convertToPrivacyPostDetailDTO(privacyPostDetail));
        }
        return ResponseEntity.ok(listDTO);
    }
}
