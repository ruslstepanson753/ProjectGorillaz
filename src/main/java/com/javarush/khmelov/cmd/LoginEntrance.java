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
        String login = req.getParameter("login");
        User user = findUser(login);
        addUserInfoToSession(req, user);
        return "start-page";
    }

    private User findUser(String login) {
        User user;
        Collection<User> allUsers= userService.getAll();
        for (User u : allUsers) {
            if (u.getLogin().equals(login)) {
                return u;
            }
        }
        return null;
    }

}
