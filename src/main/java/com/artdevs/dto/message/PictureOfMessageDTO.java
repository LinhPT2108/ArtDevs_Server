package com.artdevs.dto.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PictureOfMessageDTO {
    private Long Id;

    private Long size;

    private String url;

    private String messageId;
}
