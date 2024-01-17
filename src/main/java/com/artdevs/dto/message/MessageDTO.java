package com.artdevs.dto.message;

import java.util.Date;
import java.util.List;

import com.artdevs.domain.entities.message.PictureOfMessage;

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

    private String senderName;

    private String receiverName;

    private String status;

    private Date timeMessage;

    private String relationShipId;

    private String formUserId;

    private String toUserId;

    private List<PictureOfMessage> pictureOfMessages;
}
