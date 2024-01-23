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

import com.artdevs.domain.entities.post.Share;
import com.artdevs.dto.post.ShareDTO;
import com.artdevs.mapper.post.ShareMapper;
import com.artdevs.repositories.post.ShareRepository;
import com.artdevs.services.ShareService;
import com.artdevs.utils.Path;

@RestController
@RequestMapping(Path.path_api)
public class ShareRestController {

    @Autowired
    ShareService shareService;

    @Autowired
    ShareRepository shareRepository;

    @PostMapping("/share")
    public ResponseEntity<Share> postShare(@RequestBody ShareDTO shareDTO) {
        return ResponseEntity.ok(shareService.saveShare(ShareMapper.convertToShare(shareDTO)));
    }

    @GetMapping("/share")
    public ResponseEntity<List<ShareDTO>> getShare() {
        List<ShareDTO> listShareDTO = new ArrayList<>();
        List<Share> listShare = shareRepository.findAll();
        for (Share share : listShare) {
            listShareDTO.add(ShareMapper.convertToShareDTO(share));
        }
        return ResponseEntity.ok(listShareDTO);
    }
}
