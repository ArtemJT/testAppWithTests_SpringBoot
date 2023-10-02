package com.example.testappwithtests_springboot.service;

import com.example.testappwithtests_springboot.entity.User;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Artem Kovalov on 30.09.2023
 */
public interface UserService {

    User create(User user);

    List<User> createPack(List<User> userList);

    User getById(Long id);

    List<User> getAll();

    List<User> getAllByBirthDateRange(LocalDate from, LocalDate to);

    User updateFull(User user);

    User updateAddress(User user);

    User updatePhone(User user);

    void deleteById(Long id);
}
