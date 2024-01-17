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

import com.artdevs.domain.entities.post.ImageOfPost;
import com.artdevs.dto.post.ImageOfPostDTO;
import com.artdevs.mapper.post.ImageOfPostMapper;
import com.artdevs.services.ImageOfPostService;
import com.artdevs.utils.Path;

@RestController
@RequestMapping(Path.path_api)
public class ImageOfPostRestController {
    @Autowired
    ImageOfPostService imageofpostRepositoryService;

    @PostMapping("/imageofpost")
    public ResponseEntity<ImageOfPost> postImageOfPost(@RequestBody ImageOfPostDTO imageOfPostDTO) {
        return ResponseEntity.ok(
                imageofpostRepositoryService.saveImageOfPost(ImageOfPostMapper.convertToImageOfPost(imageOfPostDTO)));
    }

    @GetMapping("/imageofpost")
    public ResponseEntity<List<ImageOfPost>> getImageOfPost() {
        return ResponseEntity.ok(imageofpostRepositoryService.findAll());
    }
}
