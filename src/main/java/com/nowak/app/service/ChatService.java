package com.nowak.app.service;

import com.nowak.app.entities.ChatRoom;
import org.springframework.web.bind.annotation.RequestBody;


public interface ChatService {
    public void createChatRoom(@RequestBody ChatRoom chatRoom);
}

