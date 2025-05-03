package com.cweater.cweater.dto.mapping;

import com.cweater.cweater.dto.MessageDTO;
import com.cweater.cweater.entities.Message;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MessageMapping {
    MessageDTO toDTO(Message message);
}
