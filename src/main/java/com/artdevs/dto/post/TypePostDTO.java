package com.artdevs.dto.post;

import java.util.List;

import org.hibernate.annotations.Nationalized;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TypePostDTO {

    private int id;

    @Nationalized
    private String typePostName;

    private List<PostDTO> ListPostOfType;

}
