package com.artdevs.dto.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HashTagDTO {

    private int id;

    private int count;

    private String postHashtag;

    private int HashtagDetail;
}
