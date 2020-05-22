package com.nowak.app.controllers;

import com.nowak.app.dtos.Message;
import com.nowak.app.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class KafkaController {

    private final TopicService topicService;
    private final KafkaTemplate<String, Message> kafkaTemplate;
    private final SimpMessagingTemplate simpMessagingTemplate;
    List<Message> messages = new ArrayList<>();


    @PostConstruct
    public void doSomethingAfterStartup() {
    }

    @KafkaListener(topics = "chat", containerFactory = "listenerContainerFactory")
    public void listener(Message message) {
        messages.add(message);
    }
    @Autowired
    public KafkaController(KafkaTemplate<String, Message> kafkaTemplate, SimpMessagingTemplate simpMessagingTemplate, TopicService topicService) {
        this.kafkaTemplate = kafkaTemplate;
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.topicService = topicService;
    }

    @PostMapping(value = "/chat/send", consumes = "application/json", produces = "application/json")
    public void sendMessage(@RequestBody Message message) {
        message.setDate(LocalDate.now().toString());
        try {
            kafkaTemplate.send("chat", message).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @MessageMapping("/send")
    @SendTo("/app/chat")
    public Message forwardMessage(@Payload Message message) {
        return message;
    }

    @GetMapping(value = "/chat/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Message> listenMessages(Message message) {
        simpMessagingTemplate.convertAndSend("/app/chat", message);
        return messages;
    }
}
