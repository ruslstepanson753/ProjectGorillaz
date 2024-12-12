package com.javarush.khmelov.cmd;

import com.javarush.khmelov.entity.User;
import com.javarush.khmelov.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

public class LoginRegistration implements Command {
    private final UserService userService;

    public LoginRegistration(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String doPost(HttpServletRequest req) {
        User user = createUser(req);
        addUserInfoToSession(req, user);
        return "start-page";
    }

    private User createUser(HttpServletRequest req) {
        User user = User.builder()
                .login(req.getParameter("login"))
                .password(req.getParameter("password"))
                .build();
        userService.create(user);
        return user;
    }
}
