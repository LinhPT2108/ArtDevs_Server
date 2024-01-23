package com.artdevs.restcontroller.post;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artdevs.domain.entities.post.Comment;
import com.artdevs.dto.post.CommentDTO;
import com.artdevs.mapper.post.CommentMapper;
import com.artdevs.services.CommentService;
import com.artdevs.services.PostService;
import com.artdevs.services.UserService;
import com.artdevs.utils.Path;

@RestController
@RequestMapping(Path.path_api)
public class CommentRestController {
    @Autowired CommentService commentService;
    @Autowired PostService postservice;
    @Autowired UserService userservice;

    @PostMapping("/comment")
    public ResponseEntity<Comment> postComment(@RequestBody CommentDTO commentDTO) {
    	commentDTO.setTimeComment(new Date());
        return ResponseEntity.ok(commentService.saveComment(CommentMapper.convertToEntity(commentDTO,userservice,postservice)));
    }
    @PostMapping("/comment/{postid}")
    public ResponseEntity<Comment> postComment(@PathVariable("postid") String postid ,@RequestBody CommentDTO commentDTO) {
    	commentDTO.setPostID(postid);
    	commentDTO.setTimeComment(new Date());
        return ResponseEntity.ok(commentService.saveComment(CommentMapper.convertToEntity(commentDTO,userservice,postservice)));
    }
    

    @GetMapping("/comment")
    public ResponseEntity<List<CommentDTO>> getCommnet() {
        List<CommentDTO> listCommentDTO = new ArrayList<>();
        List<Comment> listComment = commentService.findAll();
        for (Comment comment : listComment) {
            listCommentDTO.add(CommentMapper.convertToCommentDTO(comment));
        }
        return ResponseEntity.ok(listCommentDTO);
    }
    @GetMapping("/comment/{postID}/page/{page}")
    public ResponseEntity<List<CommentDTO>> getCommnetByPostID(@PathVariable("postID") String postid,@PathVariable("page") int pagenumber) {
        List<CommentDTO> listCommentDTO = new ArrayList<>();
        Page<Comment> listComment = commentService.findpagecommentbyPostID(postid, pagenumber);
        for (Comment comment : listComment) {
            listCommentDTO.add(CommentMapper.convertToCommentDTO(comment));
        }
        return ResponseEntity.ok(listCommentDTO);
    }
    
    
}
