package com.javarush.stepanov.service;


import com.javarush.stepanov.dto.UserTo;

import java.util.Map;

public abstract class GameService {
    protected UserService userService;

    GameService(UserService userService) {
        this.userService = userService;
    }

    abstract Map <String,Object> processAttributes (String userAnswer, UserTo user);

    abstract void fillViewAttributes(Map<String, Object> attributesToView);

    protected void putParametrToMapIfNotNull(Map<String, Object> attributesToView, String key, Object value) {
        if (value != null) {
            attributesToView.put(key, value);
        }
    }

}
