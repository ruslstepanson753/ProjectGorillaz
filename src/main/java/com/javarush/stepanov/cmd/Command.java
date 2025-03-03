package com.javarush.stepanov.cmd;

import com.javarush.stepanov.dto.UserTo;
import com.javarush.stepanov.util.ReqHelp;
import jakarta.servlet.http.HttpServletRequest;

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

    default void addUserInfoToSession(HttpServletRequest req, UserTo userTo) {
        if(userTo!=null) {
            ReqHelp.setAttrSession(req, ATTR_USER, userTo);
            ReqHelp.setAttrSession(req, ATTR_LOGIN, userTo.getLogin());
            ReqHelp.setAttrSession(req, ATTR_GAMES_COUNT, userTo.getGamesCount());
            ReqHelp.setAttrSession(req, ATTR_WINS_COUNT, userTo.getWinsCount());
            ReqHelp.setAttrSession(req, ATTR_LOSS_COUNT, userTo.getLossCount());
        }
    }

}
