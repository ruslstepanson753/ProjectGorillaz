package com.javarush.khmelov.cmd;

import com.javarush.khmelov.entity.User;
import com.javarush.khmelov.service.UserService;

public class ComandClassAbstract implements Command {
    protected final UserService userService;

    public ComandClassAbstract(UserService userService) {
        this.userService = userService;
    }
}
