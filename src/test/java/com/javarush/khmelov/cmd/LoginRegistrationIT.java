package com.javarush.khmelov.cmd;

import com.javarush.khmelov.config.Winter;
import com.javarush.khmelov.entity.User;
import com.javarush.khmelov.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.jsp.jstl.core.Config;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ObjectInputFilter;

import static com.javarush.khmelov.storage.ConstantsCommon.GO_START;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginRegistrationIT {
    private HttpSession session;
    private HttpServletRequest req;
    private HttpServletResponse res;
    private LoginRegistration loginReg;


    @BeforeEach
    void init() {
        Config config = Winter.find(Config.class); // Если `Config` не используется, можно удалить эту строку.
        loginReg = Winter.find(LoginRegistration.class); // Инициализируем поле `loginReg`, а не создаем локальную переменную.
        session = mock(HttpSession.class);
        req = mock(HttpServletRequest.class);
        res = mock(HttpServletResponse.class);
        when(req.getSession()).thenReturn(session);
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

    
}