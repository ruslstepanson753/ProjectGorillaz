package com.javarush.khmelov.cmd;

import com.javarush.khmelov.config.Winter;
import com.javarush.khmelov.entity.User;
import com.javarush.khmelov.exception.AppException;
import com.javarush.khmelov.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.jsp.jstl.core.Config;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ObjectInputFilter;

import static com.javarush.khmelov.storage.ConstantsCommon.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginRegistrationIT extends AbstractTestClass{

    private LoginRegistration loginReg;

    @BeforeEach
    void init() {
        loginReg = Winter.find(LoginRegistration.class);
    }

    @Test
    @DisplayName("when registr then return to start with new user in session")
    void whenRegistrThenReturnToStartWithNewUserInSession() {
        when(req.getParameter("login")).thenReturn("Ivanov1");
        when(req.getParameter("password")).thenReturn("123");

        String actualRedirect = loginReg.doPost(req);
        Assertions.assertEquals(actualRedirect, GO_START);

        verify(session).setAttribute(eq("user"), any(User.class));
    }

    @Test
    @DisplayName("when empty field then exception")
    void whenEmptyFieldThenException() {
        when(req.getParameter("login")).thenReturn("");
        when(req.getParameter("password")).thenReturn("123");

        loginReg.doPost(req);

        verify(session, never()).setAttribute(eq("user"), any(User.class));

        Assertions.assertEquals(eq(ERROR_NO_ARGS),session.getAttribute(ERROR_MESSAGE));
    }

    @Test
    @DisplayName("when user is exists then error msg")
    void whenUserIsExistsThenErrorMsg() {
        when(req.getParameter("login")).thenReturn("Khmelov");
        when(req.getParameter("password")).thenReturn("123");

        String actualRedirect = loginReg.doPost(req);

        verify(session, never()).setAttribute(eq("user"), any(User.class));

        Assertions.assertEquals(eq(ERROR_USER_EXIST),session.getAttribute(ERROR_MESSAGE));
    }


}