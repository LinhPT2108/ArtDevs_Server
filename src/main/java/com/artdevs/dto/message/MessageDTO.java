package com.artdevs.dto.message;

import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.annotations.Nationalized;

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
}
