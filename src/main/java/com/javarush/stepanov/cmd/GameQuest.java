package com.javarush.stepanov.cmd;

import com.javarush.stepanov.service.UserService;
import com.javarush.stepanov.service.QuestService;
import jakarta.servlet.http.HttpServletRequest;
import static com.javarush.stepanov.constants.ConstantsCommon.*;

@SuppressWarnings(SUPPRESSWARNINGS_SET_UNUSED)
public class GameQuest implements Command {
    private final UserService userService;
    private final QuestService questService;

    public GameQuest(QuestService questService, UserService userService) {
        this.userService = userService;
        this.questService = questService;
    }

    @Override
    public String doGet(HttpServletRequest req) {
        String paramName = req.getParameter(GAME_QUEST_ATTRIBUTE_PICKED_BUTTON);
        if (paramName != null) {
            questService.setCondition(paramName);
        } else {
            questService.setStartCondition();
            String[] startViewInfo = questService.getStartViewInfo();
            fillStartRequest(req,startViewInfo);
        }

        if  (questService.questIsNotEnding()){
            String[] viewInfo = questService.getViewInfo();
            fillRequest(req,viewInfo);
        }
        questService.goNextStep();

        if (questService.lossCheck()) {
            String[] lossViewInfo = questService.getLossViewInfo();
            goToLoss(req,lossViewInfo);
        } else if (questService.winCheck()) {
            String[] lossViewInfo = questService.getWinViewInfo();
            goToWin(req,lossViewInfo);
        }

        return getView();
    }

    private void fillStartRequest(HttpServletRequest req, String[] startViews) {
        req.getSession().setAttribute(GAME_QUEST_ATTRIBUTE_IMG_EVIDENCE,startViews[0] );
        req.getSession().setAttribute(GAME_QUEST_ATTRIBUTE_IMG_GOLD, startViews[1]);
        req.getSession().setAttribute(GAME_QUEST_ATTRIBUTE_IMG_TIME, startViews[2]);
    }

    private void fillRequest(HttpServletRequest req, String[] viewInfo) {
        req.setAttribute(GAME_QUEST_ATTRIBUTE_BUTTON_LEFT, viewInfo[0]);
        req.setAttribute(GAME_QUEST_ATTRIBUTE_BUTTON_RIGHT, viewInfo[1]);
        req.setAttribute(GAME_QUEST_ATTRIBUTE_RESULT,  viewInfo[2]);
        req.setAttribute(GAME_QUEST_ATTRIBUTE_RESULT_RIGHT,  viewInfo[3]);
        req.setAttribute(GAME_QUEST_ATTRIBUTE_DESCRIPTION,  viewInfo[4]);
        req.setAttribute(GAME_QUEST_ATTRIBUTE_TIME, viewInfo[5]);
        req.setAttribute(GAME_QUEST_ATTRIBUTE_EVIDENCE, viewInfo[6]);
        req.setAttribute(GAME_QUEST_ATTRIBUTE_GOLD,  viewInfo[7]);
        req.setAttribute(GAME_QUEST_ATTRIBUTE_IMG_URL,  viewInfo[8]);
    }

    private void goToWin(HttpServletRequest req, String[] winViewInfo) {
        fillWinRequest(req, winViewInfo);
        addUserWin(req, userService);
    }

    private void fillWinRequest(HttpServletRequest req, String [] winViewInfo) {
        req.setAttribute(GAME_QUEST_ATTRIBUTE_DESCRIPTION, winViewInfo[0]);
        req.setAttribute(GAME_QUEST_ATTRIBUTE_IMG_URL,winViewInfo[1]);
        req.setAttribute(GAME_QUEST_ATTRIBUTE_IS_WIN, true);
    }

    private void goToLoss(HttpServletRequest req,String[] lossViewInfo) {
        fillLossRequest(req, lossViewInfo);
        addUserLoss(req, userService);
    }

    private void fillLossRequest(HttpServletRequest req, String[] lossViewInfo) {
        req.setAttribute(GAME_QUEST_ATTRIBUTE_LOSS_CAUSE, lossViewInfo[0]);
        req.setAttribute(GAME_QUEST_ATTRIBUTE_DESCRIPTION, lossViewInfo[1]);
        req.setAttribute(GAME_QUEST_ATTRIBUTE_IMG_URL, lossViewInfo[2]);
        req.setAttribute(GAME_QUEST_ATTRIBUTE_IS_LOSS, true);
    }

}
