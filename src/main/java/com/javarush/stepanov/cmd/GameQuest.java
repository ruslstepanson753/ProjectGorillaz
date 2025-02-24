package com.javarush.stepanov.cmd;

import com.javarush.stepanov.service.UserService;
import com.javarush.stepanov.service.QuestService;
import jakarta.servlet.http.HttpServletRequest;

@SuppressWarnings("unused")
public class GameQuest implements Command {
    private final UserService userService;
    private final QuestService questService;

    public GameQuest(QuestService questService, UserService userService) {
        this.userService = userService;
        this.questService = questService;
    }

    @Override
    public String doGet(HttpServletRequest req) {
        String paramName = req.getParameter("pickedButton");
        if (paramName != null) {
            questService.setCondition(paramName);
        } else {
            questService.setStartCondition();
            String[] startViewInfo = questService.getStartViewInfo();
            fillStartRequest(req,startViewInfo);
        }

        if  (questService.isNotEnding()){
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
        req.getSession().setAttribute("IMAGE_URL_EVIDENCE",startViews[0] );
        req.getSession().setAttribute("IMAGE_URL_GOLD", startViews[1]);
        req.getSession().setAttribute("IMAGE_URL_TIME", startViews[2]);
    }

    private void fillRequest(HttpServletRequest req, String[] viewInfo) {
        req.setAttribute("buttonLeft", viewInfo[0]);
        req.setAttribute("buttonRight", viewInfo[1]);
        req.setAttribute("result",  viewInfo[2]);
        req.setAttribute("resultRight",  viewInfo[3]);
        req.setAttribute("description",  viewInfo[4]);
        req.setAttribute("time", viewInfo[5]);
        req.setAttribute("evidence", viewInfo[6]);
        req.setAttribute("gold",  viewInfo[7]);
        req.setAttribute("imageUrl",  viewInfo[8]);
    }

    private void goToWin(HttpServletRequest req, String[] winViewInfo) {
        fillWinRequest(req, winViewInfo);
        addUserWin(req, userService);
    }

    private void fillWinRequest(HttpServletRequest req, String [] winViewInfo) {
        req.setAttribute("description", winViewInfo[0]);
        req.setAttribute("imageUrl",winViewInfo[1]);
        req.setAttribute("isWin", true);
    }

    private void goToLoss(HttpServletRequest req,String[] lossViewInfo) {
        fillLossRequest(req, lossViewInfo);
        addUserLoss(req, userService);
    }

    private void fillLossRequest(HttpServletRequest req, String[] lossViewInfo) {
        req.setAttribute("lossСause", lossViewInfo[0]);
        req.setAttribute("description", lossViewInfo[1]);
        req.setAttribute("imageUrl", lossViewInfo[2]);
        req.setAttribute("isLoss", true);
    }

}
