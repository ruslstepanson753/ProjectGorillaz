package com.javarush.stepanov.repository;

import com.javarush.stepanov.entity.UserTo;
import com.javarush.stepanov.config.SessionCreator;
import jakarta.transaction.Transactional;
@Transactional
public class UserRepository extends BaseRepository<UserTo> {
    public UserRepository(SessionCreator sessionCreator) {
        super(sessionCreator, UserTo.class);
    }
}
