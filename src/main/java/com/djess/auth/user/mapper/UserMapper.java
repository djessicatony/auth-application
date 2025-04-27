package com.djess.auth.user.mapper;

import com.djess.auth.user.dto.CreateUserRequestDto;
import com.djess.auth.user.dto.UpdateUserRequestDto;
import com.djess.auth.user.dto.UserResponseDto;
import com.djess.auth.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDto toResponseDto(User user);

    User toEntity(CreateUserRequestDto createUserRequestDto);

    // mapper for put/patch endpoints
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", ignore = true)
    void updateEntity(UpdateUserRequestDto updateUserRequestDto, @MappingTarget User user);
}
