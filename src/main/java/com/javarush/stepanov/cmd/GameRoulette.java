package com.javarush.stepanov.cmd;

import com.javarush.stepanov.dto.UserTo;
import com.javarush.stepanov.service.UserService;
import com.javarush.stepanov.service.RouletteService;
import com.javarush.stepanov.util.ReqHelp;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

import static com.javarush.stepanov.constants.ConstantsCommon.*;

@SuppressWarnings("unused")
public class GameRoulette implements Command {
    private final RouletteService rouletteService;
    private final UserService userService;

    public GameRoulette(UserService userService, RouletteService rouletteService) {
        this.userService = userService;
        this.rouletteService = rouletteService;
    }

    @Override
    public String doGet(HttpServletRequest req) {
        UserTo userTo = ReqHelp.getAttrFromSession(req, ATTR_USER);
        String userAnswer = req.getParameter(ATTR_PICKED_BUTTON);

        Map<String,Object> attributesToView = rouletteService.processAttributes(userAnswer,userTo);

        attributesToView.forEach(req::setAttribute);
        UserTo userToActual = userService.getActualUserTo(userTo);
        addUserInfoToSession(req, userToActual);

        return getView();
    }

}
