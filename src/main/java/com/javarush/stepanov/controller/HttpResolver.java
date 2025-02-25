package com.javarush.stepanov.controller;

import com.javarush.stepanov.cmd.Command;
import com.javarush.stepanov.config.NanoSpring;
import jakarta.servlet.http.HttpServletRequest;

import static com.javarush.stepanov.constants.ConstantsCommon.*;

public class HttpResolver {

    public Command resolve(HttpServletRequest request) {
        //   /cmd-example
        try {
            String requestURI = request.getRequestURI();
            requestURI = requestURI.equals(HTTP_RESOLVER_EMPTY_URI) ? GO_START : requestURI;
            String kebabName = requestURI.split("[?#/]")[1];
            String simpleName = convertKebabStyleToCamelCase(kebabName);
            String fullName = Command.class.getPackageName() + HTTP_RESOLVER_DOT + simpleName;
            Class<?> aClass = Class.forName(fullName);
            return (Command) NanoSpring.find(aClass);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String convertKebabStyleToCamelCase(String input) {
        StringBuilder result = new StringBuilder();
        boolean capitalizeNext = true;
        for (char c : input.toCharArray()) {
            if (c == '-') {
                capitalizeNext = true;
            } else {
                if (capitalizeNext) {
                    result.append(Character.toUpperCase(c));
                    capitalizeNext = false;
                } else {
                    result.append(Character.toLowerCase(c));
                }
            }
        }
        return result.toString();
    }
}
