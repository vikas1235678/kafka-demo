package com.example.kafka_demo.controller;

import com.example.kafka_demo.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {
    @Autowired
    private KafkaProducerService kafkaProducerService;

    @PostMapping("/publish")
    public ResponseEntity<String> publish(@RequestParam String message) {
        kafkaProducerService.sendMessage("foods", "south-meals");
        return ResponseEntity.ok("Message sent to Kafka");
    }
}
