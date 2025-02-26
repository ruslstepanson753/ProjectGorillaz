package com.javarush.stepanov.repository;

import com.javarush.stepanov.ContainerIT;
import com.javarush.stepanov.config.NanoSpring;
import com.javarush.stepanov.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


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
    void find() {
        User pattern = User.builder().login("newLogin").build();
        var userStream = userRepository.find(pattern);
        Assertions.assertEquals(admin, userStream.findFirst().orElseThrow());
    }

    @Test
    void update() {
        admin.setLogin("newLogin");
        userRepository.update(admin);
        User user = userRepository.get(admin.getId());
        Assertions.assertEquals(admin, user);
    }

}