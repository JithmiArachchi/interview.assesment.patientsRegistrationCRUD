package com.monitoredrx.interview.assesment.Controller;

import com.monitoredrx.interview.assesment.Service.kafkaService.KafkaProducerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    private final KafkaProducerService producer;

    public MessageController(KafkaProducerService producer) {
        this.producer = producer;
    }

    @PostMapping("/send")
    public String send(@RequestBody String msg) {
        producer.sendMessage(msg);
        return "Message sent to Kafka!";
    }
}
