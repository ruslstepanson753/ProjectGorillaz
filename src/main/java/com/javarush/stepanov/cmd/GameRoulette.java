package com.javarush.stepanov.cmd;

import com.javarush.stepanov.service.UserService;
import com.javarush.stepanov.service.RouletteService;
import jakarta.servlet.http.HttpServletRequest;
import static com.javarush.stepanov.constants.ConstantsCommon.*;

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

        String pickedColor = req.getParameter(GAME_ROULETTE_ATTRIBUTE_PICKED_BUTTON);
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
        req.setAttribute(GAME_ROULETTE_MAP_START_DESCRIPTION, startInfo[0]);
        req.setAttribute(GAME_ROULETTE_MAP_RED_BUTTON_DESCRIPTION, startInfo[1]);
        req.setAttribute(GAME_ROULETTE_MAP_BLACK_BUTTON_DESCRIPTION, startInfo[2]);
        req.setAttribute(GAME_ROULETTE_MAP_ZERO_BUTTON_DESCRIPTION, startInfo[3]);
        req.setAttribute(GAME_ROULETTE_IMAGE_URL_START, startInfo[4]);
    }

    private void fillFinishRequest(HttpServletRequest req, String[] finishInfo, String pickedColor) {
        req.setAttribute(GAME_ATTRIBUTE_IMAGE_URL, finishInfo[0]);
        req.setAttribute(GAME_ATTRIBUTE_RESULT_COLOR, finishInfo[1]);
        req.setAttribute(GAME_ATTRIBUTE_WIN_LOSS_DESCRIPTION, finishInfo[2]);
        req.setAttribute(GAME_ATTRIBUTE_IS_DONE, true);
        if (rouletteService.isWin(pickedColor)) {
            addUserWin(req, userService);
        } else {
            addUserLoss(req, userService);
        }
    }

}
