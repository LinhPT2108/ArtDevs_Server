package com.artdevs.dto.message;

import java.util.Date;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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

    @Temporal(TemporalType.TIMESTAMP)
    private Date timeMessage;
}
