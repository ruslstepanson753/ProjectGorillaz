package com.javarush.stepanov;

import com.javarush.stepanov.config.Config;
import com.javarush.stepanov.config.NanoSpring;
import com.javarush.stepanov.entity.UserTo;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BaseIT extends ContainerIT {
    protected final HttpServletRequest req;
    protected final HttpServletResponse response;
    protected final HttpSession session;
    protected final Config config;
    protected final ServletConfig servletConfig;
    protected final ServletContext servletContext;
    protected UserTo testAdmin;
    protected UserTo testUser;
    protected UserTo testGuest;

    protected BaseIT() {
        config = NanoSpring.find(Config.class);
        config.fillEmptyRepository();
        servletConfig = mock(ServletConfig.class);
        servletContext = mock(ServletContext.class);
        when(servletConfig.getServletContext()).thenReturn(servletContext);
        req = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        when(req.getSession()).thenReturn(session);
        testAdmin = UserTo.builder()
                .id(1L)
                .login("testAdmin")
                .password("testAdmin")
                .build();
        testUser = UserTo.builder()
                .id(2L)
                .login("testUser")
                .password("testUser")
                .build();
        testGuest = UserTo.builder()
                .id(3L)
                .login("testGuest")
                .password("testGuest")
                .build();
    }
}
