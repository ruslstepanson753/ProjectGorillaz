package com.javarush.khmelov.config;

import com.javarush.khmelov.entity.User;
import com.javarush.khmelov.service.UserService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Config {

    private final UserService userService;

    private final LiqubaseInit liqubaseInit;

    public void fillEmptyRepository() {
        liqubaseInit.init();

    }

}
