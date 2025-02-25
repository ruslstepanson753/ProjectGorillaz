package com.javarush.stepanov.controller;

import com.javarush.stepanov.cmd.Command;
import com.javarush.stepanov.config.Config;
import com.javarush.stepanov.config.SessionCreator;
import com.javarush.stepanov.config.NanoSpring;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import static com.javarush.stepanov.constants.ConstantsCommon.*;

@WebServlet({GO_EMPTY_ADRESS, GO_HOME, GO_START, GO_QUEST, GO_QUIZ,
        GO_ROULETTE, GO_LOGIN_ENTRANCE, GO_LOGIN_REGISTRATION, GO_STATISTIC})
public class FrontController extends HttpServlet {

    private final HttpResolver httpResolver = NanoSpring.find(HttpResolver.class);

    private static String getJsp(String view) {
        return FRONT_CONTROLLER_WEB_INF_VIEW + view + FRONT_CONTROLLER_JSP_EXTENSION;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = httpResolver.resolve(req);
        String view = command.doGet(req);
        String jsp = getJsp(view);
        req.getRequestDispatcher(jsp).forward(req, resp);
    }

    @Override
    public void init(ServletConfig servletConfig) {
        Config config = NanoSpring.find(Config.class);
        config.fillEmptyRepository();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = httpResolver.resolve(req);
        String redirect = command.doPost(req);
        resp.sendRedirect(redirect);
    }

    @Override
    public void destroy() {
        NanoSpring.find(SessionCreator.class).close();
    }
}
