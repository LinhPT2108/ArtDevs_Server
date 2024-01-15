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

import com.artdevs.domain.entities.post.Comment;
import com.artdevs.dto.post.CommentDTO;
import com.artdevs.mapper.post.CommentMapper;
import com.artdevs.repositories.post.CommentRepository;
import com.artdevs.services.impl.post.CommentServiceImpl;
import com.artdevs.utils.Path;

@RestController
@RequestMapping(Path.path_api)
public class CommentRestController {
    @Autowired
    CommentServiceImpl commentServiceImpl;

    @Autowired
    CommentRepository commentRepository;

    @PostMapping("/comment")
    public ResponseEntity<Comment> postComment(@RequestBody CommentDTO commentDTO) {
        return ResponseEntity.ok(commentServiceImpl.saveComment(CommentMapper.convertToComment(commentDTO)));
    }

    @GetMapping("/comment")
    public ResponseEntity<List<CommentDTO>> getCommnet() {
        List<CommentDTO> listCommentDTO = new ArrayList<>();
        List<Comment> listComment = commentRepository.findAll();
        for (Comment comment : listComment) {
            listCommentDTO.add(CommentMapper.convertToCommentDTO(comment));
        }
        return ResponseEntity.ok(listCommentDTO);
    }
}
