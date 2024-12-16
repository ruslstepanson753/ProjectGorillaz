package com.javarush.khmelov.cmd;

import jakarta.servlet.http.HttpServletRequest;

import static com.javarush.khmelov.storage.ConstantsCommon.*;

@SuppressWarnings("unused")
public class StartPage implements Command {
    public String doGet(HttpServletRequest request) {
        addImg(request);
        return getView();
    }

    private static void addImg(HttpServletRequest request) {
        request.getSession().setAttribute("IMG_START_HEAD", IMG_START_HEAD);
        request.getSession().setAttribute("IMG_START_QUEST", IMG_START_QUEST);
        request.getSession().setAttribute("IMG_START_ROULETTE", IMG_START_ROULETTE);
        request.getSession().setAttribute("IMG_START_QUIZ", IMG_START_QUIZ);
    }

}
