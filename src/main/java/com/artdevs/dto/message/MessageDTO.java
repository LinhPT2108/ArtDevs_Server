package com.artdevs.dto.message;

import java.sql.Timestamp;

import org.hibernate.annotations.Nationalized;

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

    @Nationalized
    private String content;

    @Nationalized
    private String subject;

    private Timestamp timeMessage;
}
