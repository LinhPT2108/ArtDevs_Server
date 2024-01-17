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

import com.artdevs.domain.entities.post.Likes;
import com.artdevs.dto.post.LikeDTO;
import com.artdevs.mapper.post.LikeMapper;
import com.artdevs.repositories.post.LikesRepository;
import com.artdevs.services.LikesService;
import com.artdevs.utils.Path;

@RestController
@RequestMapping(Path.path_api)
public class LikeRestController {
    @Autowired
    LikesService likeService;

    @Autowired
    LikesRepository likesRepository;

    @PostMapping("/like")
    public ResponseEntity<Likes> postLikes(@RequestBody LikeDTO likeDTO) {
        return ResponseEntity.ok(likeService.saveLikes(LikeMapper.convertToLikes(likeDTO)));
    }

    @GetMapping("/like")
    public ResponseEntity<List<LikeDTO>> getLikes() {
        List<LikeDTO> listLikeDTO = new ArrayList<>();
        List<Likes> listLike = likesRepository.findAll();
        for (Likes like : listLike) {
            listLikeDTO.add(LikeMapper.convertToLikesDTO(like));
        }
        return ResponseEntity.ok(listLikeDTO);
    }
}
