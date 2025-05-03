package com.cweater.cweater.dto.mapping;

import com.cweater.cweater.dto.UserDTO;
import com.cweater.cweater.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapping {
    UserDTO toDTO(User user);
}
