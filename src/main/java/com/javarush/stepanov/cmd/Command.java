package com.javarush.stepanov.cmd;

import com.javarush.stepanov.entity.User;
import com.javarush.stepanov.service.UserService;
import com.javarush.stepanov.util.ReqHelp;
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
        if(user!=null) {
            ReqHelp.setAttrSession(req, ATTR_USER, user);
            ReqHelp.setAttrSession(req, ATTR_LOGIN, user.getLogin());
            ReqHelp.setAttrSession(req, ATTR_GAMES_COUNT, user.getGamesCount());
            ReqHelp.setAttrSession(req, ATTR_WINS_COUNT, user.getWinsCount());
            ReqHelp.setAttrSession(req, ATTR_LOSS_COUNT, user.getLossCount());
        }
    }

    default void addUserLoss(HttpServletRequest req, UserService userService) {
        String gameName = getView();
        if (isLogged(req)) {
            String userName = ReqHelp.getAttrFromSession(req,ATTRIBUTE_LOGIN);
            User user = userService.findUser(userName);
            user.setLossCount(gameName);
            userService.updateUser(user);

        }
    }

    default void addUserWin(HttpServletRequest req, UserService userService) {
        String gameName = getView();
        if (isLogged(req)) {
            String userName = ReqHelp.getAttrFromSession(req,ATTRIBUTE_LOGIN);
            User user = userService.findUser(userName);
            user.setWinsCount(gameName);
            userService.updateUser(user);
            addUserInfoToSession(req, user);
        }
    }

    default boolean isLogged(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        return ((session != null) && (session.getAttribute("login") != null));
    }

}
