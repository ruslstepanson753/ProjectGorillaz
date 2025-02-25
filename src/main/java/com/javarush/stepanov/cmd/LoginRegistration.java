package com.javarush.stepanov.cmd;

import com.javarush.stepanov.entity.User;
import com.javarush.stepanov.service.UserService;
import com.javarush.stepanov.util.RequestHelpers;
import jakarta.servlet.http.HttpServletRequest;
import static com.javarush.stepanov.constants.ConstantsCommon.*;

public class LoginRegistration implements Command {
    private final UserService userService;

    public LoginRegistration(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String doPost(HttpServletRequest req) {
        String enteredLogin = req.getParameter(LOGIN_ATTRIBUTE_LOGIN);
        String enteredPassword = req.getParameter(LOGIN_ATTRIBUTE_PASSWORD);

        if ((userService.isExistLogin(enteredLogin))) {
            RequestHelpers.createError(req, ERROR_USER_EXIST);
        } else if (userService.loginOrPasswordIsEmpty(enteredLogin, enteredPassword)) {
            RequestHelpers.createError(req, ERROR_NO_ARGS);
        } else {
            User user = userService.createUser(enteredLogin, enteredPassword);
            addUserInfoToSession(req, user);
        }
        return GO_START;
    }

}
