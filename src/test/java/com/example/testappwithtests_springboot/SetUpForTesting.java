package com.example.testappwithtests_springboot;

import com.example.testappwithtests_springboot.dto.UserDto;
import com.example.testappwithtests_springboot.dto.UserIdDto;
import com.example.testappwithtests_springboot.entity.User;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Artem Kovalov on 04.10.2023
 */
class SetUpForTesting {

    static UserDto userDto = UserDto.builder()
            .id(1L)
            .firstName("Name")
            .lastName("LName")
            .email("email@mail.com")
            .birthDate(LocalDate.of(1999, 11, 11))
            .address("address")
            .phoneNumber("123456789")
            .build();
    static User user = User.builder()
            .id(1L)
            .firstName("Name")
            .lastName("LName")
            .email("email@mail.com")
            .birthDate(LocalDate.of(1999, 11, 11))
            .build();
    static List<UserDto> userDtoList = List.of(UserDto.builder()
            .id(1L)
            .firstName("Name")
            .lastName("LName")
            .email("email@mail.com")
            .birthDate(LocalDate.of(1999, 11, 11))
            .build());
    static List<User> userList = List.of(User.builder()
            .id(1L)
            .firstName("Name")
            .lastName("LName")
            .email("email@mail.com")
            .birthDate(LocalDate.of(1999, 11, 11))
            .build());
    static UserIdDto userIdDto = UserIdDto.builder().id(1L).build();
}
