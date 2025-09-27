package com.learn.project.journal.controller;

import com.learn.project.journal.service.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Autowired
    private KafkaProducer producer;

    @PostMapping("/send")
    public String sendMessage(@RequestParam String msg) {
        producer.sendMessage(msg);
        return "Message sent: " + msg;
    }
}