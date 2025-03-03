package com.javarush.stepanov.cmd;

import com.javarush.stepanov.BaseIT;
import com.javarush.stepanov.config.NanoSpring;
import com.javarush.stepanov.entity.UserTo;
import com.javarush.stepanov.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.javarush.stepanov.constants.ConstantsCommon.*;
import static org.mockito.Mockito.*;

class LoginRegistrationIT extends BaseIT {

    private LoginRegistration loginReg;

    @BeforeEach
    void init() {
        loginReg = NanoSpring.find(LoginRegistration.class);
    }

    @Test
    @DisplayName("when registr then return to start with new user in session")
    void whenRegistrThenReturnToStartWithNewUserInSession() {
        when(req.getParameter(ATTR_LOGIN)).thenReturn("Ivanov9");
        when(req.getParameter(ATTR_PASSWORD)).thenReturn("124");

        String actualRedirect = loginReg.doPost(req);
        Assertions.assertEquals(actualRedirect, GO_START);

        verify(session).setAttribute(eq(ATTR_USER), any(UserTo.class));

        deleteLastUser();
    }

    private static void deleteLastUser() {
        UserRepository userRepository = NanoSpring.find(UserRepository.class);
        List<UserTo> users = (List<UserTo>)userRepository.getAll();
        UserTo actualUser = users.get(users.size() - 1);
        userRepository.delete(actualUser);
    }

    @Test
    @DisplayName("when empty field then exception")
    void whenEmptyFieldThenException() {
        when(req.getParameter(ATTR_LOGIN)).thenReturn("");
        when(req.getParameter(ATTR_PASSWORD)).thenReturn("123");

        loginReg.doPost(req);

        verify(session, never()).setAttribute(eq(ATTR_USER), any(UserTo.class));

        Assertions.assertEquals(eq(ERROR_NO_ARGS), session.getAttribute(ERROR_MESSAGE));
    }

    @Test
    @DisplayName("when user is exists then error msg")
    void whenUserIsExistsThenErrorMsg() {
        when(req.getParameter(ATTR_LOGIN)).thenReturn("Khmelov");
        when(req.getParameter(ATTR_PASSWORD)).thenReturn("123");

        String actualRedirect = loginReg.doPost(req);

        verify(session, never()).setAttribute(eq(ATTR_USER), any(UserTo.class));

        Assertions.assertEquals(eq(ERROR_USER_EXIST), session.getAttribute(ERROR_MESSAGE));
    }


}