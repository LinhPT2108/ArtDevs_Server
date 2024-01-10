package com.artdevs.dto;

import org.hibernate.annotations.Nationalized;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PictureDTO {

    private int id;

    private String cloudinaryPublicId;

    @Nationalized
    private String description;

    private String imageUrl;

    private String userId;
}
