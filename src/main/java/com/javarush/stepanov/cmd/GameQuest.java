package com.javarush.stepanov.cmd;

import com.javarush.stepanov.entity.User;
import com.javarush.stepanov.service.UserService;
import com.javarush.stepanov.service.QuestService;
import com.javarush.stepanov.util.ReqHelp;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

import static com.javarush.stepanov.constants.ConstantsCommon.*;

@SuppressWarnings(SUPPRESSWARNINGS_SET_UNUSED)
public class GameQuest implements Command {
    private final QuestService questService;

    public GameQuest(QuestService questService, UserService userService) {
        this.questService = questService;
    }

    @Override
    public String doGet(HttpServletRequest req) {
        User user = ReqHelp.getAttrFromSession(req, ATTR_USER);
        String pickedButton = req.getParameter("pickedButton");

        Map <String,Object> attributesToView = questService.processAttributes(pickedButton,user);

        attributesToView.forEach(req::setAttribute);
        addUserInfoToSession(req, user);

        return getView();
    }

}
