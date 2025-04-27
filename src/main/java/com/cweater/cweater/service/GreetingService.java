package com.cweater.cweater.service;

import com.cweater.cweater.config.OwnUserDetail;
import com.cweater.cweater.entities.Message;
import com.cweater.cweater.repository.MessageRepo;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GreetingService {

    private final MessageRepo messageRepo;
    public GreetingService(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }


    public String main(Map<String, Object> model) {
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);
        return "main";
    }

    public String add(OwnUserDetail userDetail, String text, String tag, Map<String, Object> model) {
        Message msg = new Message(text, tag, userDetail.getUser());
        messageRepo.save(msg);

        Iterable< Message> messages = messageRepo.findAll();
        model.put("messages", messages);
        return "main";
    }

    public String filter(String filter, Map<String, Object> model) {
        Iterable< Message> messages;
        if (filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByTag(filter);
        } else {
            messages = messageRepo.findAll();
        }

        model.put("messages", messages);
        return "main";
    }


}
