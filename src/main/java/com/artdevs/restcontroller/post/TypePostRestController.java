package com.artdevs.restcontroller.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artdevs.domain.entities.post.TypePost;
import com.artdevs.dto.post.TypePostDTO;
import com.artdevs.mapper.post.TypePostMapper;
import com.artdevs.repositories.post.TypepostRepository;
import com.artdevs.utils.Path;

@RestController
@RequestMapping(Path.path_api)
public class TypePostRestController {
    @Autowired
    TypepostRepository typepostRepository;

    @PostMapping("/typepost")
    public ResponseEntity<TypePost> postTypePost(@RequestBody TypePostDTO typePostDTO) {
        return ResponseEntity.ok(typepostRepository.save(TypePostMapper.convertToTypePost(typePostDTO)));
    }

    @GetMapping("/typepost")
    public ResponseEntity<List<TypePost>> getTypePost() {
        return ResponseEntity.ok(typepostRepository.findAll());
    }
}
