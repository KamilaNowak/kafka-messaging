package com.nowak.app.service;

import com.nowak.app.entities.ChatRoom;
import com.nowak.app.repositories.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Service
public class ChatServiceImpl  implements ChatService{

    private final ChatRoomRepository chatRoomRepository;

    public ChatServiceImpl(ChatRoomRepository chatRoomRepository) {
        this.chatRoomRepository = chatRoomRepository;
    }

    @Override
    public void createChatRoom(@RequestBody ChatRoom chatRoom){
        chatRoom.setId(UUID.randomUUID().toString());
        chatRoomRepository.save(chatRoom);
    }
}
