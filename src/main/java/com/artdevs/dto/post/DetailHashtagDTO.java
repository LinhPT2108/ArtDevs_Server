package com.artdevs.dto.post;

import java.util.List;

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

    private String hashtagText;

    private List<HashTagDTO> ListHashtagOfDetail;
}
