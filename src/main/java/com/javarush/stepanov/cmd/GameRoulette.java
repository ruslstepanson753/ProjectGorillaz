package com.javarush.stepanov.cmd;

import com.javarush.stepanov.entity.UserTo;
import com.javarush.stepanov.service.UserService;
import com.javarush.stepanov.service.RouletteService;
import com.javarush.stepanov.util.ReqHelp;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

import static com.javarush.stepanov.constants.ConstantsCommon.*;

@SuppressWarnings("unused")
public class GameRoulette implements Command {
    private final RouletteService rouletteService;

    public GameRoulette(UserService userService, RouletteService rouletteService) {
        this.rouletteService = rouletteService;
    }

    @Override
    public String doGet(HttpServletRequest req) {

        UserTo user = ReqHelp.getAttrFromSession(req, ATTR_USER);
        String userAnswer = req.getParameter(ATTR_PICKED_BUTTON);

        Map<String,Object> attributesToView = rouletteService.processAttributes(userAnswer,user);

        attributesToView.forEach(req::setAttribute);
        addUserInfoToSession(req, user);

        return getView();
    }

}
