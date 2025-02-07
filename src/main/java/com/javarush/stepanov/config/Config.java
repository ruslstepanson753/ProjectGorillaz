package com.javarush.stepanov.config;

import com.javarush.stepanov.service.UserService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Config {

    private final UserService userService;

    private final LiqubaseInit liqubaseInit;

    public void fillEmptyRepository() {
        liqubaseInit.init();
    }

}
