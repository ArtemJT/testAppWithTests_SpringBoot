package com.example.testappwithtests_springboot.util.mapper;

import com.example.testappwithtests_springboot.dto.UserDto;
import com.example.testappwithtests_springboot.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Artem Kovalov on 01.10.2023
 */
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "isDeleted", ignore = true)
    User toEntity(UserDto dto);

    UserDto toDto(User entity);

    List<UserDto> toDtoList(List<User> entityList);

    @Mapping(target = "isDeleted", ignore = true)
    List<User> toEntityList(List<UserDto> dtoList);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isDeleted", ignore = true)
    void update(@MappingTarget User target, User source);
}
