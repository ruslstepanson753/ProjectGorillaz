package com.javarush.khmelov.cmd;

import jakarta.servlet.http.HttpServletRequest;

import static com.javarush.khmelov.storage.ConstantsCommon.IMG_START_HEAD;
import static com.javarush.khmelov.storage.ConstantsCommon.IMG_START_QUEST;

@SuppressWarnings("unused")
public class StartPage implements Command {
    public String doGet(HttpServletRequest request) {
        addImg(request);
        return getView();
    }

    private static void addImg(HttpServletRequest request) {
        request.getSession().setAttribute("IMG_START_QUEST", IMG_START_QUEST);
        request.getSession().setAttribute("IMG_START_HEAD", IMG_START_HEAD);
    }

}
