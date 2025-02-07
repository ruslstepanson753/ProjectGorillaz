package com.javarush.stepanov.repository;

import com.javarush.stepanov.entity.User;
import com.javarush.stepanov.config.SessionCreator;

public class UserRepository extends BaseRepository<User> {

    public UserRepository(SessionCreator sessionCreator) {
        super(sessionCreator, User.class);
    }
}
