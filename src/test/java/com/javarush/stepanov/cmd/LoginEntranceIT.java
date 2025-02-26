package com.javarush.stepanov.cmd;

import com.javarush.stepanov.config.NanoSpring;
import com.javarush.stepanov.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.javarush.stepanov.constants.ConstantsCommon.*;
import static org.mockito.Mockito.*;

class LoginEntranceIT extends AbstractTestClass {

    private LoginEntrance loginEntr;

    @BeforeEach
    void init() {
        loginEntr = NanoSpring.find(LoginEntrance.class);
    }

    @Test
    @DisplayName("when log then return to start with  user in session")
    void whenLogThenReturnToStartWithNeUserInSession() {
        when(req.getParameter(LOGIN_ATTRIBUTE_LOGIN)).thenReturn("Khmelov");
        when(req.getParameter(LOGIN_ATTRIBUTE_PASSWORD)).thenReturn("admin");

        String actualRedirect = loginEntr.doPost(req);
        Assertions.assertEquals(actualRedirect, GO_START);

        verify(session).setAttribute(eq(ATTRIBUTE_USER), any(User.class));
    }

    @Test
    @DisplayName("when empty field then exception")
    void whenEmptyFieldThenException() {
        when(req.getParameter(LOGIN_ATTRIBUTE_LOGIN)).thenReturn("");
        when(req.getParameter(LOGIN_ATTRIBUTE_PASSWORD)).thenReturn("123");

        loginEntr.doPost(req);

        verify(session, never()).setAttribute(eq(ATTRIBUTE_USER), any(User.class));

        Assertions.assertEquals(eq(ERROR_NO_ARGS), session.getAttribute(ERROR_MESSAGE));
    }

    @Test
    @DisplayName("when invalid data then error msg")
    void whenInvalidDataThenErrorMsg() {
        when(req.getParameter(LOGIN_ATTRIBUTE_LOGIN)).thenReturn("Khmelov");
        when(req.getParameter(LOGIN_ATTRIBUTE_PASSWORD)).thenReturn("123");

        loginEntr.doPost(req);

        verify(session, never()).setAttribute(eq(ATTRIBUTE_USER), any(User.class));

        Assertions.assertEquals(eq(ERROR_PASSWORD_OR_LOGIN_INCORRECT), session.getAttribute(ERROR_MESSAGE));
    }

}