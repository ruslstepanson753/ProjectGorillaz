package com.javarush.khmelov.cmd;

import com.javarush.khmelov.entity.User;
import com.javarush.khmelov.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface Command {

    default String doGet(HttpServletRequest request) {
        return getView();
    }

    default String doPost(HttpServletRequest request) {
        return getView();
    }

    default String getView() {
        String simpleName = this.getClass().getSimpleName();
        return convertCamelCaseToKebabStyle(simpleName);
    }

    private static String convertCamelCaseToKebabStyle(String string) {
        String snakeName = string.chars()
                .mapToObj(s -> String.valueOf((char) s))
                .flatMap(s -> s.matches("[A-Z]")
                        ? Stream.of("-", s)
                        : Stream.of(s))
                .collect(Collectors.joining())
                .toLowerCase();
        return snakeName.startsWith("-")
                ? snakeName.substring(1)
                : snakeName;
    }

    default void addUserInfoToSession(HttpServletRequest req, User user) {
        req.getSession().setAttribute("user", user);
        req.getSession().setAttribute("login", user.getLogin());
        req.getSession().setAttribute("gamescount", user.getGamesCount());
        req.getSession().setAttribute("winscount", user.getWinsCount());
        req.getSession().setAttribute("losscount", user.getLossCount());
    }

    default User findUser(HttpServletRequest req, UserService userService) {

        Object loginAttribute = req.getSession().getAttribute("login");
        String login;
        if (loginAttribute != null) {
             login = loginAttribute.toString();
            // Дальнейшая обработка переменной login
        } else {
           return null;
        }
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
