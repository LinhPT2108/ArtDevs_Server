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

import com.artdevs.domain.entities.post.TypePost;
import com.artdevs.dto.post.TypePostDTO;
import com.artdevs.mapper.post.TypePostMapper;
import com.artdevs.repositories.post.TypepostRepository;
import com.artdevs.services.TypePostService;
import com.artdevs.utils.Path;

@RestController
@RequestMapping(Path.path_api)
public class TypePostRestController {
    @Autowired
    TypePostService typepostRepositoryService;

    @Autowired
    TypepostRepository typepostRepository;

    @PostMapping("/typepost")
    public ResponseEntity<TypePost> postTypePost(@RequestBody TypePostDTO typePostDTO) {
        return ResponseEntity
                .ok(typepostRepositoryService.saveTypePost(TypePostMapper.convertToTypePost(typePostDTO)));
    }

    @GetMapping("/typepost")
    public ResponseEntity<List<TypePostDTO>> getTypePost() {
        List<TypePostDTO> listTypePostDTO = new ArrayList<>();
        List<TypePost> listTypePost = typepostRepository.findAll();
        for (TypePost typePost : listTypePost) {
            listTypePostDTO.add(TypePostMapper.convertToTypePostDTO(typePost));
        }
        return ResponseEntity.ok(listTypePostDTO);
    }
}
