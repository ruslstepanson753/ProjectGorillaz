package com.javarush.stepanov.repository;

import com.javarush.stepanov.config.NanoSpring;
import com.javarush.stepanov.entity.User;
import com.javarush.stepanov.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.javarush.stepanov.ContainerIT;


class UserRepositoryIT extends ContainerIT {

    private final UserRepository userRepository = NanoSpring.find(UserRepository.class);
    private User admin;

    @BeforeEach
    void createAdmin() {
        admin = userRepository.get(1L);
    }

    @Test
    void get() {
        User user = userRepository.get(admin.getId());
        Assertions.assertEquals(admin, user);
    }

    @Test
    void update() {
        admin.setLogin("newLogin");
        userRepository.update(admin);
        User user = userRepository.get(admin.getId());
        Assertions.assertEquals(admin, user);
    }

}