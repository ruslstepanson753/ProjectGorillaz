package com.javarush.khmelov.cmd;

import com.javarush.khmelov.config.Winter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.jsp.jstl.core.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AbstractTestClass {
    protected HttpSession session;
    protected HttpServletRequest req;
    protected HttpServletResponse res;

    @BeforeEach
    void initCommon() {
        Config config = Winter.find(Config.class); // Если `Config` не используется, можно удалить эту строку.
        session = mock(HttpSession.class);
        req = mock(HttpServletRequest.class);
        res = mock(HttpServletResponse.class);
        when(req.getSession()).thenReturn(session);
    }
}
