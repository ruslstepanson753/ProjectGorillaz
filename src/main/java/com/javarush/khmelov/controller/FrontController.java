package com.javarush.khmelov.controller;

import com.javarush.khmelov.cmd.Command;
import com.javarush.khmelov.config.Winter;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet({"", "/home", "/start-page", "/game-quest", "/game-quiz",
        "/game-roulette", "/login-entrance", "/login-registration"})
public class FrontController extends HttpServlet {

    private final HttpResolver httpResolver = Winter.find(HttpResolver.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = httpResolver.resolve(req);
        String view = command.doGet(req);
        String jsp = getJsp(view);
        req.getRequestDispatcher(jsp).forward(req, resp);
    }

    @Override
    public void init(ServletConfig config) {

    }

    private static String getJsp(String view) {
        return "/WEB-INF/" + view + ".jsp";
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = httpResolver.resolve(req);
        String redirect = command.doPost(req);
        resp.sendRedirect(redirect);
    }
}
