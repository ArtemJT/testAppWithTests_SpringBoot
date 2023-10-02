package com.example.testappwithtests_springboot.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * @author Artem Kovalov on 30.09.2023
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
@Entity
@Table(schema = "test_schema", name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column
    private String address;

    @Column
    private String phoneNumber;

    @Column
    @Builder.Default
    private Boolean isDeleted = Boolean.FALSE;
}
