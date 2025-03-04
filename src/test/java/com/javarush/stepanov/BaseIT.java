package com.javarush.stepanov;

import com.javarush.stepanov.config.Config;
import com.javarush.stepanov.config.NanoSpring;
import com.javarush.stepanov.entity.User;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;

public class BaseIT extends ContainerIT {
    protected final HttpServletRequest req;
    protected final HttpServletResponse response;
    protected final HttpSession session;
    protected final Config config;
    protected final ServletConfig servletConfig;
    protected final ServletContext servletContext;
    protected User testAdmin;
    protected User testUser;
    protected User testGuest;

    protected BaseIT() {
        config = NanoSpring.find(Config.class);
        config.fillEmptyRepository();
        servletConfig = Mockito.mock(ServletConfig.class);
        servletContext = Mockito.mock(ServletContext.class);
        Mockito.when(servletConfig.getServletContext()).thenReturn(servletContext);
        req = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        Mockito.when(req.getSession()).thenReturn(session);
        testAdmin = User.builder()
                .id(1L)
                .login("testAdmin")
                .password("testAdmin")
                .build();
        testUser = User.builder()
                .id(2L)
                .login("testUser")
                .password("testUser")
                .build();
        testGuest = User.builder()
                .id(3L)
                .login("testGuest")
                .password("testGuest")
                .build();
    }
}
