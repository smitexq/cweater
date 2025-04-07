package com.cweater.cweater.repository;

import com.cweater.cweater.entities.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Long> {
    List<Message> findByTag(String tag);
}
