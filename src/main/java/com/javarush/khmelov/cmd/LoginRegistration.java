package com.javarush.khmelov.cmd;

import com.javarush.khmelov.entity.User;
import com.javarush.khmelov.service.UserService;
import com.javarush.khmelov.util.RequestHelpers;
import jakarta.servlet.http.HttpServletRequest;

import static com.javarush.khmelov.storage.ConstantsCommon.ERROR_NO_ARGS;
import static com.javarush.khmelov.storage.ConstantsCommon.ERROR_USER_EXIST;

public class LoginRegistration implements Command {
    private final UserService userService;
    private String enteredLogin;
    private String enteredPassword;

    public LoginRegistration(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String doPost(HttpServletRequest req) {
        if (validTest(req)){
            User user = createUser(req);
            addUserInfoToSession(req, user);
        }
        return "start-page";
    }

    private boolean validTest(HttpServletRequest req) {
        enteredLogin = req.getParameter("login");
        enteredPassword = req.getParameter("password");
        if (isEmptyArg(req,enteredLogin)) return false;
        if (isEmptyArg(req,enteredLogin)) return false;
        if (userService.isExistLogin(enteredLogin)) {
            RequestHelpers.createError(req, ERROR_USER_EXIST);
            return false;
        }
        return true;
    }

    private User createUser(HttpServletRequest req) {
        enteredLogin = req.getParameter("login");
        enteredPassword = req.getParameter("password");
        User user = User.builder()
                .login(enteredLogin)
                .password(enteredPassword)
                .build();
        userService.create(user);
        return user;
    }
}
