package com.example.testappwithtests_springboot.util;

import com.example.testappwithtests_springboot.dto.UserAddressUpdateDto;
import com.example.testappwithtests_springboot.dto.UserDto;
import com.example.testappwithtests_springboot.dto.UserPhoneUpdateDto;
import com.example.testappwithtests_springboot.entity.User;
import org.mapstruct.InheritInverseConfiguration;
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

    User toEntity(UserDto dto);

    @InheritInverseConfiguration
    UserDto toDto(User entity);

    @Mapping(target = "isDeleted", ignore = true)
    User updateAddressDtoToEntity(UserAddressUpdateDto dto);

    @Mapping(target = "isDeleted", ignore = true)
    User updatePhoneDtoToEntity(UserPhoneUpdateDto dto);


    List<UserDto> toDtoList(List<User> entityList);

    List<User> toEntityList(List<UserDto> dtoList);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isDeleted", ignore = true)
    void update(@MappingTarget User target, User source);
}
