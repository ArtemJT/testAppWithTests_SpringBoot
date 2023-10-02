package com.example.testappwithtests_springboot.util;

import com.example.testappwithtests_springboot.dto.UserAddressUpdateDto;
import com.example.testappwithtests_springboot.dto.UserPhoneUpdateDto;
import com.example.testappwithtests_springboot.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * @author Artem Kovalov on 01.10.2023
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapperUnmappedPolicy {
    UserMapperUnmappedPolicy INSTANCE = Mappers.getMapper(UserMapperUnmappedPolicy.class);

    User updateAddressDtoToEntity(UserAddressUpdateDto dto);

    User updatePhoneDtoToEntity(UserPhoneUpdateDto dto);
}
