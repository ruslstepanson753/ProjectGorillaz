package com.javarush.stepanov.cmd;

import com.javarush.stepanov.service.UserService;
import com.javarush.stepanov.service.RouletteService;
import com.javarush.stepanov.util.UrlHelper;
import jakarta.servlet.http.HttpServletRequest;


@SuppressWarnings("unused")
public class GameRoulette implements Command {
    private final UserService userService;
    private final RouletteService rouletteService;

    public GameRoulette(UserService userService, RouletteService rouletteService) {
        this.userService = userService;
        this.rouletteService = rouletteService;
    }

    @Override
    public String doGet(HttpServletRequest req) {

        String pickedColor = req.getParameter("pickedButton");
        if (pickedColor == null) {
            String[] startInfo = rouletteService.getStartInfo();
            fillStartRequest(req, startInfo);
        } else {
            String[] finishInfo = rouletteService.getFinishInfo(pickedColor);
            fillFinishRequest(req, finishInfo, pickedColor);
        }

        return getView();
    }

    private void fillStartRequest(HttpServletRequest req, String[] startInfo) {
        req.setAttribute("START_DESCRIPTION", startInfo[0]);
        req.setAttribute("RED_BUTTON_DESCRIPTION", startInfo[1]);
        req.setAttribute("BLACK_BUTTON_DESCRIPTION", startInfo[2]);
        req.setAttribute("ZERO_BUTTON_DESCRIPTION", startInfo[3]);
        req.setAttribute("IMAGE_URL_START", startInfo[4]);
    }

    private void fillFinishRequest(HttpServletRequest req, String[] finishInfo, String pickedColor) {
        req.setAttribute("imageUrl", finishInfo[0]);
        req.setAttribute("resultColor", finishInfo[1]);
        req.setAttribute("winLossDescription", finishInfo[2]);
        req.setAttribute("isDone", true);
        if (rouletteService.isWin(pickedColor)) {
            addUserWin(req, userService);
        } else {
            addUserLoss(req, userService);
        }
    }

}
