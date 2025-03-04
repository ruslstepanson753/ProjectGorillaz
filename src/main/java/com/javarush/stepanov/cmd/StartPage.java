package com.javarush.stepanov.cmd;

import com.javarush.stepanov.util.ReqHelp;
import jakarta.servlet.http.HttpServletRequest;
import static com.javarush.stepanov.constants.ConstantsCommon.*;

@SuppressWarnings("unused")
public class StartPage implements Command {
    private static void addConstatnsToSession(HttpServletRequest request) {
        ReqHelp.setAttrSession(request, ATTR_IMG_START_HEAD, IMG_START_HEAD);
        ReqHelp.setAttrSession(request, ATTR_IMG_START_QUEST, IMG_START_QUEST);
        ReqHelp.setAttrSession(request, ATTR_IMG_START_ROULETTE, IMG_START_ROULETTE);
        ReqHelp.setAttrSession(request, ATTR_IMG_START_QUIZ, IMG_START_QUIZ);
        ReqHelp.setAttrSession(request, ATTR_AUDIO_START_FOOTER, AUDIO_START_FOOTER);
    }

    public String doGet(HttpServletRequest request) {
        addConstatnsToSession(request);
        return getView();
    }

}
