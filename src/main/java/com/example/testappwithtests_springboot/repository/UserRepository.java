package com.example.testappwithtests_springboot.repository;

import com.example.testappwithtests_springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Artem Kovalov on 30.09.2023
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByBirthDateBetweenAndIsDeletedIsFalse(LocalDate from, LocalDate to);


}
