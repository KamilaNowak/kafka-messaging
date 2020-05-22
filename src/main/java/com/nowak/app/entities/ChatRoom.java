package com.nowak.app.entities;

import lombok.Data;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class ChatRoom {

    private String id;
    private String roomName;
    private String creator;
    private String creationDate;

}
