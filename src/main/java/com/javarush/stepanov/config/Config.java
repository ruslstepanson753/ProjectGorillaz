package com.javarush.stepanov.config;

import com.javarush.stepanov.entity.User;
//import com.javarush.stepanov.service.QuestService;
import com.javarush.stepanov.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Transactional
public class Config {

    private final LiqubaseInit liqubaseInit;

    public void fillEmptyRepository() {
        liqubaseInit.start();
    }
}
