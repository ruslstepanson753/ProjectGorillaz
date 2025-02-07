package com.javarush.stepanov.cmd;

import com.javarush.stepanov.config.NanoSpring;
import com.javarush.stepanov.entity.User;
import com.javarush.stepanov.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.javarush.stepanov.constants.ConstantsCommon.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginRegistrationIT extends AbstractTestClass {

    private LoginRegistration loginReg;

    @BeforeEach
    void init() {
        loginReg = NanoSpring.find(LoginRegistration.class);
    }

    @Test
    @DisplayName("when registr then return to start with new user in session")
    void whenRegistrThenReturnToStartWithNewUserInSession() {
        when(req.getParameter("login")).thenReturn("Ivanov9");
        when(req.getParameter("password")).thenReturn("124");

        String actualRedirect = loginReg.doPost(req);
        Assertions.assertEquals(actualRedirect, GO_START);

        verify(session).setAttribute(eq("user"), any(User.class));

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
        when(req.getParameter("login")).thenReturn("");
        when(req.getParameter("password")).thenReturn("123");

        loginReg.doPost(req);

        verify(session, never()).setAttribute(eq("user"), any(User.class));

        Assertions.assertEquals(eq(ERROR_NO_ARGS), session.getAttribute(ERROR_MESSAGE));
    }

    @Test
    @DisplayName("when user is exists then error msg")
    void whenUserIsExistsThenErrorMsg() {
        when(req.getParameter("login")).thenReturn("Khmelov");
        when(req.getParameter("password")).thenReturn("123");

        String actualRedirect = loginReg.doPost(req);

        verify(session, never()).setAttribute(eq("user"), any(User.class));

        Assertions.assertEquals(eq(ERROR_USER_EXIST), session.getAttribute(ERROR_MESSAGE));
    }


}