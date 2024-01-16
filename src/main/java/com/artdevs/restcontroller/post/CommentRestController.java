package com.artdevs.restcontroller.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artdevs.domain.entities.post.Comment;
import com.artdevs.dto.post.CommentDTO;
import com.artdevs.mapper.post.CommentMapper;
import com.artdevs.services.impl.post.CommentServiceImpl;
import com.artdevs.utils.Path;

@RestController
@RequestMapping(Path.path_api)
public class CommentRestController {
    @Autowired
    CommentServiceImpl commentServiceImpl;

    @PostMapping("/comment")
    public ResponseEntity<Comment> postComment(@RequestBody CommentDTO commentDTO) {
        return ResponseEntity.ok(commentServiceImpl.saveComment(CommentMapper.convertToEntity(commentDTO)));
    }

    @GetMapping("/comment")
    public ResponseEntity<List<Comment>> getCommnet() {
        return ResponseEntity.ok(commentServiceImpl.findAll());
    }
}
