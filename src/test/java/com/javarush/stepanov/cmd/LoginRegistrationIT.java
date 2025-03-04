package com.javarush.stepanov.cmd;

import com.javarush.stepanov.cmd.LoginRegistration;
import com.javarush.stepanov.config.NanoSpring;
import com.javarush.stepanov.entity.User;
import com.javarush.stepanov.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import com.javarush.stepanov.BaseIT;
import java.util.List;

import static com.javarush.stepanov.constants.ConstantsCommon.*;

class LoginRegistrationIT extends BaseIT {

    private LoginRegistration loginReg;

    @BeforeEach
    void init() {
        loginReg = NanoSpring.find(LoginRegistration.class);
    }

    @Test
    @DisplayName("when registr then return to start with new user in session")
    void whenRegistrThenReturnToStartWithNewUserInSession() {
        Mockito.when(req.getParameter(ATTR_LOGIN)).thenReturn("Ivanov9");
        Mockito.when(req.getParameter(ATTR_PASSWORD)).thenReturn("124");

        String actualRedirect = loginReg.doPost(req);
        Assertions.assertEquals(actualRedirect, GO_START);

        Mockito.verify(session).setAttribute(ArgumentMatchers.eq(ATTR_USER), ArgumentMatchers.any(User.class));

        deleteLastUser();
    }

    private static void deleteLastUser() {
        UserRepository userRepository = NanoSpring.find(UserRepository.class);
        List<User> users = (List<User>)userRepository.getAll();
        User actualUser = users.get(users.size() - 1);
        userRepository.delete(actualUser);
    }

    @Test
    @DisplayName("when empty field then exception")
    void whenEmptyFieldThenException() {
        Mockito.when(req.getParameter(ATTR_LOGIN)).thenReturn("");
        Mockito.when(req.getParameter(ATTR_PASSWORD)).thenReturn("123");

        loginReg.doPost(req);

        Mockito.verify(session, Mockito.never()).setAttribute(ArgumentMatchers.eq(ATTR_USER), ArgumentMatchers.any(User.class));

        Assertions.assertEquals(ArgumentMatchers.eq(ERROR_NO_ARGS), session.getAttribute(ERROR_MESSAGE));
    }

    @Test
    @DisplayName("when user is exists then error msg")
    void whenUserIsExistsThenErrorMsg() {
        Mockito.when(req.getParameter(ATTR_LOGIN)).thenReturn("Khmelov");
        Mockito.when(req.getParameter(ATTR_PASSWORD)).thenReturn("123");

        String actualRedirect = loginReg.doPost(req);

        Mockito.verify(session, Mockito.never()).setAttribute(ArgumentMatchers.eq(ATTR_USER), ArgumentMatchers.any(User.class));

        Assertions.assertEquals(ArgumentMatchers.eq(ERROR_USER_EXIST), session.getAttribute(ERROR_MESSAGE));
    }

}