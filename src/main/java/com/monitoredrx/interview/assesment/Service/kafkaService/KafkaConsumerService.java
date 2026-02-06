package com.monitoredrx.interview.assesment.Service.kafkaService;

import com.monitoredrx.interview.assesment.config.WebSocketMessageHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
@Service
public class KafkaConsumerService {
    private final MessageService messageService;

    public KafkaConsumerService(MessageService messageService) {
        this.messageService = messageService;
    }

    @KafkaListener(topics = "message-topic", groupId = "message-group")
    public void consume(String message) {
        messageService.saveMessage(message);
        WebSocketMessageHandler.broadcastMessage(message);  // React එකට push කරනවා
    }
}