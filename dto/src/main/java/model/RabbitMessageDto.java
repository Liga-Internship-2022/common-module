package model;

import lombok.Data;

@Data
public class RabbitMessageDto {
    private MessageType messageType;
    private String content;
}
