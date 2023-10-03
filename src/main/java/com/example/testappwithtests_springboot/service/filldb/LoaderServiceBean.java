package com.example.testappwithtests_springboot.service.filldb;

import com.example.testappwithtests_springboot.entity.User;
import com.example.testappwithtests_springboot.service.UserService;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * @author Artem Kovalov on 01.10.2023
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class LoaderServiceBean implements LoaderService {

    private final UserService userService;
    private final Random random = new Random();

    @Value("${min-age}")
    private String age;

    @Override
    public void generateData(int amount) {
        List<User> users = createUsersList(amount);
        userService.createPack(users);
    }

    private List<User> createUsersList(int amount) {
        List<User> users = new ArrayList<>();
        long seed = 1;

        Faker faker = new Faker(new Locale("en"), new Random(seed));
        for (int i = 0; i < amount; i++) {
            String fstName = faker.name().firstName();
            String lstName = faker.name().lastName();
            String email = (fstName + '_' + lstName).toLowerCase() + "@mail.com";
            LocalDate birthDate = generateBirthDate();

            User user = User.builder()
                    .firstName(fstName)
                    .lastName(lstName)
                    .email(email)
                    .birthDate(birthDate)
                    .build();

            users.add(user);
        }
        return users;
    }

    private LocalDate generateBirthDate() {
        long minAge = Long.parseLong(age);
        int maxYear = LocalDate.now().minusYears(minAge).getYear();
        int minYear = maxYear - 100;
        int maxDay;

        int year = random.nextInt(maxYear - minYear + 1) + minYear;

        int month = random.nextInt(12) + 1;
        if (month == 2) {
            boolean leapYear = LocalDate.of(year, 1, 1).isLeapYear();
            if (leapYear) {
                maxDay = 29;
            } else {
                maxDay = 28;
            }
        } else if (month % 2 == 0 && month != 8 || month == 9 || month == 11) {
            maxDay = 30;
        } else {
            maxDay = 31;
        }

        int day = random.nextInt(maxDay) + 1;

        return LocalDate.of(year, month, day);
    }
}
