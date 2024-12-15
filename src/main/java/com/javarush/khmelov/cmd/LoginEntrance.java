package com.javarush.khmelov.cmd;

import com.javarush.khmelov.entity.User;
import com.javarush.khmelov.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Collection;

public class LoginEntrance implements Command {
    private final UserService userService;

    public LoginEntrance(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String doPost(HttpServletRequest req) {
        User user = findUser(req,userService);
        addUserInfoToSession(req, user);
        return "start-page";
    }



}
