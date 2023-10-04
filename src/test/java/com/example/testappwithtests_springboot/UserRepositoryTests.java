package com.example.testappwithtests_springboot;

import com.example.testappwithtests_springboot.entity.User;
import com.example.testappwithtests_springboot.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;
import java.util.List;

import static com.example.testappwithtests_springboot.SetUpForTesting.user;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Artem Kovalov on 04.10.2023
 */
@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("User Repository Tests")
class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    @DisplayName("Save user test")
    void saveEmployeeTest() {
        User created = userRepository.save(user);

        assertThat(created.getId()).isPositive();
        assertThat(created.getId()).isEqualTo(1);
        assertThat(created.getFirstName()).isEqualTo("Name");
    }

    @Test
    @Order(2)
    @DisplayName("Get user by id test")
    void findUserByIdTest() {
        User found = userRepository.findById(1L).orElseThrow();

        assertThat(found.getId()).isPositive();
        assertThat(found.getId()).isEqualTo(1);
        assertThat(found.getFirstName()).isEqualTo("Name");
    }

    @Test
    @Order(3)
    @DisplayName("Get list of users test")
    void findAllTest() {
        List<User> found = userRepository.findAll();

        assertThat(found).isNotEmpty();
        assertThat(found.get(0).getId()).isPositive();
        assertThat(found.get(0).getId()).isEqualTo(1);
        assertThat(found.get(0).getFirstName()).isEqualTo("Name");
    }

    @Test
    @Order(4)
    @DisplayName("Get list of users by birth date test")
    void findAllByBirthDateBetweenAndIsDeletedIsFalseTest() {
        LocalDate from = LocalDate.of(1998, 10, 1);
        LocalDate to = LocalDate.of(2000, 10, 1);

        List<User> found = userRepository.findAllByBirthDateBetweenAndIsDeletedIsFalse(from, to);

        assertThat(found).isNotEmpty();
        assertThat(found.get(0).getId()).isPositive();
        assertThat(found.get(0).getId()).isEqualTo(1);
        assertThat(found.get(0).getFirstName()).isEqualTo("Name");

        List<User> empty = userRepository.findAllByBirthDateBetweenAndIsDeletedIsFalse(to, from);

        assertThat(empty).isEmpty();
    }

    @Test
    @Order(5)
    @Rollback(value = false)
    @DisplayName("Update user test")
    void updateTest() {
        User found = userRepository.findById(1L).orElseThrow();
        found.setFirstName("UPDATE");

        assertThat(found.getFirstName()).isEqualTo("UPDATE");
    }

}
