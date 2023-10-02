package com.example.testappwithtests_springboot.web;

import com.example.testappwithtests_springboot.dto.UserAddressUpdateDto;
import com.example.testappwithtests_springboot.dto.UserDto;
import com.example.testappwithtests_springboot.dto.UserIdDto;
import com.example.testappwithtests_springboot.dto.UserPhoneUpdateDto;
import com.example.testappwithtests_springboot.entity.User;
import com.example.testappwithtests_springboot.service.UserService;
import com.example.testappwithtests_springboot.util.mapper.UserMapper;
import com.example.testappwithtests_springboot.util.mapper.UserMapperUnmappedPolicy;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Artem Kovalov on 01.10.2023
 */
@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(value = "/demo", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final UserMapperUnmappedPolicy userMapperUnmapped;

    @PostMapping("users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto saveUser(@RequestBody @Valid UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        return userMapper.toDto(userService.create(user));
    }

    @PostMapping("users/all")
    @ResponseStatus(HttpStatus.CREATED)
    public List<UserDto> saveUsersPack(@RequestBody @Valid List<UserDto> userDtoList) {
        List<User> userList = userMapper.toEntityList(userDtoList);
        return userMapper.toDtoList(userService.createPack(userList));
    }

    @GetMapping("users")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserById(@RequestBody @Valid UserIdDto userIdDto) {
        return userMapper.toDto(userService.getById(userIdDto.id()));
    }

    @GetMapping("users/all")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getAllUsers() {
        return userMapper.toDtoList(userService.getAll());
    }

    @GetMapping("users/all/range")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getAllByBirthDateRange(
            @RequestParam
            @NotNull
            @DateTimeFormat(pattern = "'YYYY-MM-DD'")
            LocalDate from,
            @RequestParam
            @NotNull
            @DateTimeFormat(pattern = "'YYYY-MM-DD'")
            LocalDate to) {
        return userMapper.toDtoList(userService.getAllByBirthDateRange(from, to));
    }

    @PutMapping("users")
    @ResponseStatus(HttpStatus.OK)
    public UserDto fullUpdateUser(@RequestBody @Valid UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        return userMapper.toDto(userService.updateFull(user));
    }

    @PatchMapping("users/address")
    @ResponseStatus(HttpStatus.OK)
    public UserDto addressUpdateUser(@RequestBody @Valid UserAddressUpdateDto userDto) {
        User user = userMapperUnmapped.updateAddressDtoToEntity(userDto);
        return userMapper.toDto(userService.updateAddress(user));
    }

    @PatchMapping("users/phone")
    @ResponseStatus(HttpStatus.OK)
    public UserDto phoneUpdateUser(@RequestBody @Valid UserPhoneUpdateDto userDto) {
        User user = userMapperUnmapped.updatePhoneDtoToEntity(userDto);
        return userMapper.toDto(userService.updatePhone(user));
    }

    @DeleteMapping("users")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@RequestBody @Valid UserIdDto userIdDto) {
        userService.deleteById(userIdDto.id());
    }
}
