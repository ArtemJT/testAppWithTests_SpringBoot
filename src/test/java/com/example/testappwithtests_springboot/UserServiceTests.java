package com.example.testappwithtests_springboot;

import com.example.testappwithtests_springboot.entity.User;
import com.example.testappwithtests_springboot.repository.UserRepository;
import com.example.testappwithtests_springboot.service.UserServiceBean;
import com.example.testappwithtests_springboot.util.exception.DateRangeException;
import com.example.testappwithtests_springboot.util.mapper.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.example.testappwithtests_springboot.SetUpForTesting.user;
import static com.example.testappwithtests_springboot.SetUpForTesting.userList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Artem Kovalov on 04.10.2023
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("User Service Tests")
class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceBean userService;

    @Test
    @DisplayName("Save user test")
    void createTest() {
        when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(user);
        User created = userService.create(user);

        assertThat(created.getFirstName()).isSameAs(user.getFirstName());

        verify(userRepository).save(ArgumentMatchers.any(User.class));
    }

    @Test
    @DisplayName("Save list of users test")
    void createPackTest() {
        when(userRepository.saveAll(ArgumentMatchers.anyList())).thenReturn(userList);
        List<User> created = userService.createPack(userList);

        assertThat(created.get(0).getFirstName()).isSameAs(user.getFirstName());

        verify(userRepository).saveAll(ArgumentMatchers.anyList());
    }

    @Test
    @DisplayName("Get user by id test")
    void getByIdTest() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.findById(-1L)).thenThrow(EntityNotFoundException.class);
        User found = userService.getById(1L);

        assertThat(found.getFirstName()).isSameAs(user.getFirstName());
        assertThrows(EntityNotFoundException.class, () -> userService.getById(-1L));

        verify(userRepository).findById(1L);
    }

    @Test
    @DisplayName("Get list of users test")
    void getAllTest() {
        when(userRepository.findAll()).thenReturn(userList);
        List<User> found = userService.getAll();

        assertThat(found.get(0).getFirstName()).isSameAs(user.getFirstName());

        verify(userRepository).findAll();
    }

    @Test
    @DisplayName("Get list of users by birth date range test")
    void getAllByBirthDateRangeTest() {
        LocalDate from = LocalDate.of(1998, 10, 1);
        LocalDate to = LocalDate.of(2000, 10, 1);

        when(userRepository.findAllByBirthDateBetweenAndIsDeletedIsFalse(from, to)).thenReturn(userList);

        List<User> found = userService.getAllByBirthDateRange(from, to);
        assertThat(found.get(0).getFirstName()).isSameAs(user.getFirstName());

        when(userRepository.findAllByBirthDateBetweenAndIsDeletedIsFalse(to, from)).thenThrow(DateRangeException.class);

        assertThrows(DateRangeException.class, () -> userService.getAllByBirthDateRange(to, from));

        verify(userRepository).findAllByBirthDateBetweenAndIsDeletedIsFalse(from, to);
    }

    @Test
    @DisplayName("Update user test")
    void updateFullTest() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);
        User updated = userService.updateFull(user);

        assertThat(updated.getFirstName()).isSameAs(user.getFirstName());

        verify(userRepository).save(any(User.class));
    }
}
