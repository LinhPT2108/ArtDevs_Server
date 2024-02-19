package com.artdevs.services.impl.post;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.artdevs.domain.entities.post.Comment;
import com.artdevs.domain.entities.post.PictureOfComment;
import com.artdevs.domain.entities.post.ReplyComment;
import com.artdevs.repositories.post.CommentRepository;
import com.artdevs.repositories.post.PictureOfCommentRepository;
import com.artdevs.repositories.post.ReplyCommentRepository;
import com.artdevs.repositories.user.UserRepository;
import com.artdevs.services.CloudinaryService;
import com.artdevs.services.CloudinaryValidationService;
import com.artdevs.services.PictureOfCommentService;

public class PictureOfCommentServiceImpl implements PictureOfCommentService {

    @Autowired
    PictureOfCommentRepository pictureOfCommentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CloudinaryService cloudinaryService;

    @Autowired
    CloudinaryValidationService cloudinaryValidation;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    ReplyCommentRepository replyCommentRepository;

    @Override
    public PictureOfComment findPictureOfCommentById(Long pictureofcommentId) {
        Optional<PictureOfComment> picOptional = pictureOfCommentRepository.findById(pictureofcommentId);
        return picOptional.orElse(null);
    }

    @Override
    public List<PictureOfComment> findAll() {
        return pictureOfCommentRepository.findAll();
    }

    @Override
    public PictureOfComment savePictureOfComment(Long commentId, Long relycommentId, MultipartFile file)
            throws Exception {
        Comment comment = this.commentRepository.findById(commentId).orElse(null);
        if (comment == null) {
            return null;
        }

        ReplyComment replyComment = this.replyCommentRepository.findById(relycommentId).orElse(null);
        if (replyComment == null) {
            return null;
        }

        String cloundinaryPublicId = UUID.randomUUID().toString();
        Map uploadMap = this.cloudinaryService.uploadImage(file, cloundinaryPublicId);

        if (!cloudinaryValidation.isValid(uploadMap)) {
            return null;
        }

        PictureOfComment pictureOfComment = new PictureOfComment();
        pictureOfComment.setImageUrl(uploadMap.get("url").toString());
        pictureOfComment.setSize(null);
        pictureOfComment.setPictureOfCommentId(comment);
        pictureOfComment.setPictureOfReplyCommentId(replyComment);
        pictureOfComment.setCloudinaryPublicId(uploadMap.get("public_id").toString());
        return this.pictureOfCommentRepository.save(pictureOfComment);
    }

    @Override
    public PictureOfComment updatePictureOfComment(PictureOfComment pictureOfComment) {
        return pictureOfCommentRepository.save(pictureOfComment);
    }

    @Override
    public void deletePictureOfComment(PictureOfComment pictureOfComment) {
        pictureOfCommentRepository.delete(pictureOfComment);
    }

}
