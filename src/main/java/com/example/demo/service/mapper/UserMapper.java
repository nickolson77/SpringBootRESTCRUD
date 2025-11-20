package com.example.demo.service.mapper;

import com.example.demo.repository.User;
import com.example.demo.service.dto.UserResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "birth", target = "dob")
    UserResponseDTO mapUserToUserResponseDTO(User user);
    List<UserResponseDTO> mapUserToUserResponseDTO(List<User> users);
}
