package com.example.testappwithtests_springboot;

import com.example.testappwithtests_springboot.dto.UserAddressUpdateDto;
import com.example.testappwithtests_springboot.dto.UserDto;
import com.example.testappwithtests_springboot.dto.UserPhoneUpdateDto;
import com.example.testappwithtests_springboot.entity.User;
import com.example.testappwithtests_springboot.service.UserService;
import com.example.testappwithtests_springboot.util.mapper.UserMapper;
import com.example.testappwithtests_springboot.util.mapper.UserMapperUnmappedPolicy;
import com.example.testappwithtests_springboot.web.UserController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static com.example.testappwithtests_springboot.SetUpForTesting.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Artem Kovalov on 03.10.2023
 */
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = UserController.class)
@DisplayName("User Controller Tests")
class UserControllerTests {

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private UserService userService;

    @MockBean
    private UserMapper userMapper;

    @MockBean
    private UserMapperUnmappedPolicy userMapperUnmapped;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("POST /demo/users")
    void saveUserTest() throws Exception {

        when(userMapper.toEntity(any(UserDto.class))).thenReturn(user);
        when(userMapper.toDto(any(User.class))).thenReturn(userDto);
        when(userService.create(any(User.class))).thenReturn(user);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/demo/users")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(user));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("Name"))
                .andReturn();

        verify(userService).create(any());
    }

    @Test
    @DisplayName("POST /demo/users/all")
    void saveUsersPackTest() throws Exception {

        when(userMapper.toEntityList(anyList())).thenReturn(userList);
        when(userMapper.toDtoList(anyList())).thenReturn(userDtoList);
        when(userService.createPack(anyList())).thenReturn(userList);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/demo/users/all")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(userList));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.*").isNotEmpty())
                .andExpect(jsonPath("$[*].firstName").value("Name"))
                .andReturn();

        verify(userService).createPack(anyList());
    }

    @Test
    @DisplayName("GET /demo/users")
    void getUserByIdTest() throws Exception {

        when(userMapper.toDto(any(User.class))).thenReturn(userDto);
        when(userService.getById(anyLong())).thenReturn(user);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .get("/demo/users")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(userIdDto));

        mockMvc.perform(mockRequest)
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.firstName").value("Name"))
                .andReturn();

        verify(userService).getById(anyLong());
    }

    @Test
    @DisplayName("GET /demo/users/all")
    void getAllUsersTest() throws Exception {
        when(userMapper.toDtoList(anyList())).thenReturn(userDtoList);
        when(userService.getAll()).thenReturn(userList);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .get("/demo/users/all");

        mockMvc.perform(mockRequest)
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.*").isNotEmpty())
                .andExpect(jsonPath("$[*].firstName").value("Name"))
                .andReturn();

        verify(userService).getAll();
    }

    @Test
    @DisplayName("GET /demo/users/all/range")
    void getAllByBirthDateRangeTest() throws Exception {
        when(userMapper.toDtoList(anyList())).thenReturn(userDtoList);
        when(userService.getAllByBirthDateRange(isA(LocalDate.class), isA(LocalDate.class))).thenReturn(userList);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .get("/demo/users/all/range")
                .param("from", "1999-11-11")
                .param("to", "2023-11-11");

        mockMvc.perform(mockRequest)
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.*").isNotEmpty())
                .andExpect(jsonPath("$[*].firstName").value("Name"))
                .andReturn();

        verify(userService).getAllByBirthDateRange(isA(LocalDate.class), isA(LocalDate.class));
    }

    @Test
    @DisplayName("PUT /demo/users")
    void fullUpdateUser() throws Exception {
        when(userMapper.toEntity(any(UserDto.class))).thenReturn(user);
        when(userMapper.toDto(any(User.class))).thenReturn(userDto);
        when(userService.updateFull(any(User.class))).thenReturn(user);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .put("/demo/users")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(user));

        mockMvc.perform(mockRequest)
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.firstName").value("Name"))
                .andReturn();

        verify(userService).updateFull(any());
    }

    @Test
    @DisplayName("PUT /demo/users")
    void fullUpdateUserTest() throws Exception {
        when(userMapper.toEntity(any(UserDto.class))).thenReturn(user);
        when(userMapper.toDto(any(User.class))).thenReturn(userDto);
        when(userService.updateFull(any(User.class))).thenReturn(user);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .put("/demo/users")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(user));

        mockMvc.perform(mockRequest)
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.firstName").value("Name"))
                .andReturn();

        verify(userService).updateFull(any());
    }

    @Test
    @DisplayName("PATCH /demo/users/address")
    void addressUpdateUserTest() throws Exception {
        UserAddressUpdateDto updateDto = UserAddressUpdateDto.builder()
                .id(1L)
                .address("address")
                .build();

        when(userMapperUnmapped.updateAddressDtoToEntity(any(UserAddressUpdateDto.class))).thenReturn(user);
        when(userMapper.toDto(any(User.class))).thenReturn(userDto);
        when(userService.updateAddress(any(User.class))).thenReturn(user);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .patch("/demo/users/address")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(updateDto));

        mockMvc.perform(mockRequest)
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.firstName").value("Name"))
                .andReturn();

        verify(userService).updateAddress(any());
    }

    @Test
    @DisplayName("PATCH /demo/users/phone")
    void phoneUpdateUserTest() throws Exception {
        UserPhoneUpdateDto updateDto = UserPhoneUpdateDto.builder()
                .id(1L)
                .phoneNumber("123456789")
                .build();

        when(userMapperUnmapped.updatePhoneDtoToEntity(any(UserPhoneUpdateDto.class))).thenReturn(user);
        when(userMapper.toDto(any(User.class))).thenReturn(userDto);
        when(userService.updatePhone(any(User.class))).thenReturn(user);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .patch("/demo/users/phone")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(updateDto));

        mockMvc.perform(mockRequest)
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.firstName").value("Name"))
                .andReturn();

        verify(userService).updatePhone(any());
    }

    @Test
    @DisplayName("DELETE /demo/users")
    void deleteUserById() throws Exception {
        doNothing().when(userService).deleteById(anyLong());

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .delete("/demo/users")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(userIdDto));

        mockMvc.perform(mockRequest)
                .andExpect(status().isNoContent())
                .andReturn();

        verify(userService).deleteById(anyLong());
    }
}
