package com.javarush.stepanov.cmd;

import com.javarush.stepanov.entity.User;
import com.javarush.stepanov.service.UserService;
import com.javarush.stepanov.util.RequestHelpers;
import jakarta.servlet.http.HttpServletRequest;
import static com.javarush.stepanov.constants.ConstantsCommon.*;

public class LoginEntrance implements Command {
    private final UserService userService;

    public LoginEntrance(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String doPost(HttpServletRequest req) {
        String enteredLogin = req.getParameter(LOGIN_ATTRIBUTE_LOGIN);
        String enteredPassword = req.getParameter(LOGIN_ATTRIBUTE_PASSWORD);

        if ((userService.loginOrPasswordIsIncorrect(enteredLogin, enteredPassword))) {
            RequestHelpers.createError(req, ERROR_PASSWORD_OR_LOGIN_INCORRECT);
        } else if (userService.loginOrPasswordIsEmpty(enteredLogin, enteredPassword)) {
            RequestHelpers.createError(req, ERROR_NO_ARGS);
        } else {
            User user = userService.findUser(enteredLogin);
            addUserInfoToSession(req, user);
        }
        return GO_START;
    }

}
