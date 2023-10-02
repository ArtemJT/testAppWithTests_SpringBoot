package com.example.testappwithtests_springboot.service;

import com.example.testappwithtests_springboot.entity.User;
import com.example.testappwithtests_springboot.repository.UserRepository;
import com.example.testappwithtests_springboot.util.mapper.UserMapper;
import com.example.testappwithtests_springboot.util.exception.ResourceWasDeletedException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Artem Kovalov on 30.09.2023
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceBean implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> createPack(List<User> userList) {
        return userRepository.saveAll(userList);
    }

    @Override
    public User getById(Long id) throws EntityNotFoundException {
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User updateFull(User user) {
        User userExist = getById(user.getId());
        checkIsDeleted(userExist);
        userMapper.update(userExist, user);
        return userRepository.save(userExist);
    }

    @Override
    public User updateAddress(User user) {
        User userExist = getById(user.getId());
        checkIsDeleted(userExist);
        userExist.setAddress(user.getAddress());
        return userRepository.save(userExist);
    }

    @Override
    public User updatePhone(User user) {
        User userExist = getById(user.getId());
        checkIsDeleted(userExist);
        userExist.setPhoneNumber(user.getPhoneNumber());
        return userRepository.save(userExist);
    }

    @Override
    public void deleteById(Long id) {
        User user = getById(id);
        checkIsDeleted(user);
        user.setIsDeleted(Boolean.TRUE);
        userRepository.save(user);
    }

    @Override
    public List<User> getAllByBirthDateRange(LocalDate from, LocalDate to) {
        return userRepository.findAllByBirthDateBetweenAndIsDeletedIsFalse(from, to);
    }

    private void checkIsDeleted(User user) {
        if (Boolean.TRUE.equals(user.getIsDeleted())) {
            throw new ResourceWasDeletedException("User with id=" + user.getId() + " was deleted");
        }
    }
}
