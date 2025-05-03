package com.cweater.cweater.service;

import com.cweater.cweater.config.OwnUserDetail;
import com.cweater.cweater.dto.MessageDTO;
import com.cweater.cweater.dto.mapping.MessageMapping;
import com.cweater.cweater.entities.Message;
import com.cweater.cweater.repository.MessageRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class GreetingService {

    private final MessageRepo messageRepo;
    private final MessageMapping mapping;
    public GreetingService(MessageRepo messageRepo, MessageMapping mapping) {
        this.messageRepo = messageRepo;
        this.mapping = mapping;
    }


    public String main(Map<String, Object> model) {
        Iterable<Message> messages = messageRepo.findAll();

//        ArrayList<MessageDTO> msg = new ArrayList<>();
//        for (Message x: messages) {
//            msg.add(mapping.toDTO(x));
//        }
        List<MessageDTO> output = StreamSupport.stream(messages.spliterator(), false).map(x -> mapping.toDTO(x)).collect(Collectors.toList());

        model.put("messages", output);
        return "main";
    }

    public String add(OwnUserDetail userDetail, String text, String tag, Map<String, Object> model) {
        Message msg = new Message(text, tag, userDetail.getUser());
        messageRepo.save(msg);

        Iterable< Message> messages = messageRepo.findAll();
        List<MessageDTO> output = StreamSupport.stream(messages.spliterator(), false).map(x -> mapping.toDTO(x)).collect(Collectors.toList());

        model.put("messages", output);
        return "main";
    }

    public String filter(String filter, Map<String, Object> model) {
        Iterable< Message> messages;
        if (filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByTag(filter);
        } else {
            messages = messageRepo.findAll();
        }

        List<MessageDTO> output = StreamSupport.stream(messages.spliterator(), false).map(x -> mapping.toDTO(x)).collect(Collectors.toList());

        model.put("messages", output);
        return "main";
    }


}
