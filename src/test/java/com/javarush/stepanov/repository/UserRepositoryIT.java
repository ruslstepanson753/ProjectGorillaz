package com.javarush.stepanov.repository;

import com.javarush.stepanov.ContainerIT;
import com.javarush.stepanov.config.NanoSpring;
import com.javarush.stepanov.entity.UserTo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class UserRepositoryIT extends ContainerIT {

    private final UserRepository userRepository = NanoSpring.find(UserRepository.class);
    private UserTo admin;

    @BeforeEach
    void createAdmin() {
        admin = userRepository.get(1L);
    }

    @Test
    void get() {
        UserTo user = userRepository.get(admin.getId());
        Assertions.assertEquals(admin, user);
    }

    @Test
    void update() {
        admin.setLogin("newLogin");
        userRepository.update(admin);
        UserTo user = userRepository.get(admin.getId());
        Assertions.assertEquals(admin, user);
    }

}