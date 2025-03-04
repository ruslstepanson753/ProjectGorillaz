package com.javarush.stepanov.cmd;

import com.javarush.stepanov.cmd.LoginEntrance;
import com.javarush.stepanov.config.NanoSpring;
import com.javarush.stepanov.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import com.javarush.stepanov.BaseIT;

import static com.javarush.stepanov.constants.ConstantsCommon.*;

class LoginEntranceIT extends BaseIT {

    private LoginEntrance loginEntr;

    @BeforeEach
    void init() {
        loginEntr = NanoSpring.find(LoginEntrance.class);
    }

    @Test
    @DisplayName("when log then return to start with  user in session")
    void whenLogThenReturnToStartWithNeUserInSession() {
        Mockito.when(req.getParameter(ATTR_LOGIN)).thenReturn("Khmelov");
        Mockito.when(req.getParameter(ATTR_PASSWORD)).thenReturn("admin");

        String actualRedirect = loginEntr.doPost(req);
        Assertions.assertEquals(actualRedirect, GO_START);

        Mockito.verify(session).setAttribute(ArgumentMatchers.eq(ATTR_USER), ArgumentMatchers.any(User.class));
    }

    @Test
    @DisplayName("when empty field then exception")
    void whenEmptyFieldThenException() {
        Mockito.when(req.getParameter(ATTR_LOGIN)).thenReturn("");
        Mockito.when(req.getParameter(ATTR_PASSWORD)).thenReturn("123");

        loginEntr.doPost(req);

        Mockito.verify(session, Mockito.never()).setAttribute(ArgumentMatchers.eq(ATTR_USER), ArgumentMatchers.any(User.class));

        Assertions.assertEquals(ArgumentMatchers.eq(ERROR_NO_ARGS), session.getAttribute(ERROR_MESSAGE));
    }

    @Test
    @DisplayName("when invalid data then error msg")
    void whenInvalidDataThenErrorMsg() {
        Mockito.when(req.getParameter(ATTR_LOGIN)).thenReturn("Khmelov");
        Mockito.when(req.getParameter(ATTR_PASSWORD)).thenReturn("123");

        loginEntr.doPost(req);

        Mockito.verify(session, Mockito.never()).setAttribute(ArgumentMatchers.eq(ATTR_USER), ArgumentMatchers.any(User.class));

        Assertions.assertEquals(ArgumentMatchers.eq(ERROR_PASSWORD_OR_LOGIN_INCORRECT), session.getAttribute(ERROR_MESSAGE));
    }

}