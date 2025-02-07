package com.javarush.khmelov.controller;

import com.javarush.khmelov.cmd.Command;
import com.javarush.khmelov.config.Config;
import com.javarush.khmelov.config.LiqubaseInit;
import com.javarush.khmelov.config.SessionCreator;
import com.javarush.khmelov.config.NanoSpring;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.javarush.khmelov.storage.ConstantsCommon.*;

@WebServlet({GO_EMPTY_ADRESS, GO_HOME, GO_START, GO_QUEST, GO_QUIZ,
        GO_ROULETTE, GO_LOGIN_ENTRANCE, GO_LOGIN_REGISTRATION})
public class FrontController extends HttpServlet {

    private final HttpResolver httpResolver = NanoSpring.find(HttpResolver.class);

    private static String getJsp(String view) {
        return "/WEB-INF/" + view + ".jsp";
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
        SessionCreator.createSession();
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
