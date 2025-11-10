package com.example.SkillBridge.messaging.consumers;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MessageDTO {

    private Long userId;
    private String messageType;
    private String content;
    private LocalDateTime timestamp;

    public MessageDTO() {
        this.timestamp = LocalDateTime.now();
    }

    public MessageDTO(Long userId, String messageType, String content) {
        this();
        this.userId = userId;
        this.messageType = messageType;
        this.content = content;
    }
}