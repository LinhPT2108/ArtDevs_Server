package com.artdevs.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.artdevs.domain.entities.post.PictureOfComment;

public interface PictureOfCommentService {

    PictureOfComment findPictureOfCommentById(Long pictureofcommentId);

    List<PictureOfComment> findAll();

    PictureOfComment savePictureOfComment(Long commentId, Long relycommentId, MultipartFile file) throws Exception;

    PictureOfComment updatePictureOfComment(PictureOfComment pictureOfComment);

    void deletePictureOfComment(PictureOfComment pictureOfComment);
}
