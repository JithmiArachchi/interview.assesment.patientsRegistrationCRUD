package com.monitoredrx.interview.assesment.Service.kafkaService;

import com.monitoredrx.interview.assesment.Repos.KaffkaRepo.MessageRepository;
import com.monitoredrx.interview.assesment.model.Message;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void saveMessage(String content) {
        Message msg = Message.builder().content(content).build();
        messageRepository.save(msg);
    }
}