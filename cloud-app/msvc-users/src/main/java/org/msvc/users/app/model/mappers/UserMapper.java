package org.msvc.users.app.model.mappers;

import org.mapstruct.Mapper;
import org.msvc.users.app.model.dtos.UserDTO;
import org.msvc.users.app.model.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(User user);

    User toEntity(UserDTO userDTO);
}
