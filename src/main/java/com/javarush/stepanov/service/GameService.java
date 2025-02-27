package com.javarush.stepanov.service;

import com.javarush.stepanov.entity.User;

import java.util.Map;

public abstract class GameService {

    UserService userService;

    GameService(UserService userService) {
        this.userService = userService;
    }

    abstract Map <String,Object> processAttributes (String userAnswer, User user);

    abstract void fillViewAttributes(Map<String, Object> attributesToView);

    void putParametrToMapIfNotNull(Map<String, Object> attributesToView, String key, Object value) {
        if (value != null) {
            attributesToView.put(key, value);
        }
    }


}
