package com.javarush.stepanov.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import static com.javarush.stepanov.constants.ConstantsCommon.*;

@WebFilter({GO_EMPTY_ADRESS, GO_HOME, GO_START, GO_QUEST, GO_QUIZ,
        GO_ROULETTE, GO_LOGIN_ENTRANCE, GO_LOGIN_REGISTRATION,GO_STATISTIC})
public class ErrorCleanerFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(req, res);
        HttpSession session = req.getSession(false);
        if (req.getMethod().equals(ERROR_CLEANER_REQ_NAME_GET) && session != null) {
            session.removeAttribute(ERROR_MESSAGE);
        }
    }
}
