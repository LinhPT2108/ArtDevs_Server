package com.artdevs.dto.post;

import java.util.List;

import org.hibernate.annotations.Nationalized;

import com.artdevs.domain.entities.post.HashTag;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetailHashtagDTO {

    private int id;

    @Nationalized
    private String hashtagText;

    private List<HashTag> listHashTag;
}
