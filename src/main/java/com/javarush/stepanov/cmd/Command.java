package com.javarush.stepanov.cmd;

import com.javarush.stepanov.entity.User;
import com.javarush.stepanov.service.UserService;
import com.javarush.stepanov.util.RequestHelpers;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.javarush.stepanov.constants.ConstantsCommon.*;

public interface Command {

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

    default void addUserInfoToSession(HttpServletRequest req, User user) {
        req.getSession().setAttribute(COMMAND_ATTRIBUTE_USER, user);
        req.getSession().setAttribute(COMMAND_ATTRIBUTE_LOGIN, user.getLogin());
        req.getSession().setAttribute(COMMAND_ATTRIBUTE_GAMES_COUNT, user.getGamesCount());
        req.getSession().setAttribute(COMMAND_ATTRIBUTE_WINS_COUNT, user.getWinsCount());
        req.getSession().setAttribute(COMMAND_ATTRIBUTE_LOSS_COUNT, user.getLossCount());
    }

    default void addUserLoss(HttpServletRequest req, UserService userService) {
        String gameName = getView();
        if (isLogged(req)) {
            String userName = RequestHelpers.getNameUserFromReq(req);
            User user = userService.findUser(userName);
            user.setLossCount(gameName);
            userService.updateUser(user);
            addUserInfoToSession(req, user);
        }
    }

    default void addUserWin(HttpServletRequest req, UserService userService) {
        String gameName = getView();
        if (isLogged(req)) {
            String userName = RequestHelpers.getNameUserFromReq(req);
            User user = userService.findUser(userName);
            user.setWinsCount(gameName);
            userService.updateUser(user);
            addUserInfoToSession(req, user);
        }
    }

    private boolean isLogged(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        return ((session != null) && (session.getAttribute("login") != null));
    }

}
