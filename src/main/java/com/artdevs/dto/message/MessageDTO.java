package com.artdevs.dto.message;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {

    private String messageId;

    private String content;

    private String subject;

    private Timestamp timeMessage;
}
