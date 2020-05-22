package com.nowak.app.controllers;

import com.nowak.app.entities.ChatRoom;
import com.nowak.app.repositories.ChatRoomRepository;
import com.nowak.app.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ChatRoomsController {

   private final ChatService chatService;

    public ChatRoomsController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping(value = "/room/create")
    public void createChatRoom(@RequestBody ChatRoom chatRoom){
        chatService.createChatRoom(chatRoom);
    }
}
