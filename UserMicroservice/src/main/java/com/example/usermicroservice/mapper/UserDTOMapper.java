package com.example.usermicroservice.mapper;

import com.example.usermicroservice.dto.UserRequestDTO;
import com.example.usermicroservice.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserDTOMapper {

    UserDTOMapper INSTANCE = Mappers.getMapper(UserDTOMapper.class);

    UserRequestDTO userToUserDTO(User user);

    User userDTOToUser(UserRequestDTO userRequestDTO);

    void updateUserFromDTO(UserRequestDTO userRequestDTO, @MappingTarget User user);


}
