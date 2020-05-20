package com.nowak.app.controllers;

import com.nowak.app.entities.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class KafkaController {

    private final KafkaTemplate<String, Message> kafkaTemplate;

    @Autowired
    public KafkaController(KafkaTemplate<String, Message> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping(value = "/chat/send",  consumes = "application/json", produces = "application/json")
    public void sendMessage(@RequestBody Message message){
        message.setDate(LocalDate.now().toString());
        try{
            kafkaTemplate.send("chat", message).get();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
