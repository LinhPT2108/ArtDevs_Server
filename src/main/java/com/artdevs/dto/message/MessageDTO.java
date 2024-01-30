package com.artdevs.dto.message;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MessageDTO {

    private String message;

    private String status;

    private Date timeMessage;

    private int relationShipId;

    private String formUserId;

    private String toUserId;

    private List<PictureOfMessageDTO> pictureOfMessages;
}
