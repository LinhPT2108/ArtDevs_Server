package com.artdevs.dto.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PictureOfCommentDTO {

    private Long id;

    private Long size;

    private String imageUrl;

    private String cloudinaryPublicId;

    private Long pictureOfCommentId;

    private Long pictureOfReplyCommentId;
}
