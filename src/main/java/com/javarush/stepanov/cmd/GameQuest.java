package com.javarush.stepanov.cmd;

import com.javarush.stepanov.dto.UserTo;
import com.javarush.stepanov.service.UserService;
import com.javarush.stepanov.service.QuestService;
import com.javarush.stepanov.util.ReqHelp;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import static com.javarush.stepanov.constants.ConstantsCommon.*;

@SuppressWarnings(SET_ATTR_UNUSED)
public class GameQuest implements Command {
    private final QuestService questService;
    private final UserService userService;
    public GameQuest(QuestService questService, UserService userService) {
        this.userService = userService;
        this.questService = questService;
    }

    @Override
    public String doGet(HttpServletRequest req) {
        UserTo userTo = ReqHelp.getAttrFromSession(req, ATTR_USER);
        String pickedButton = req.getParameter(ATTR_PICKED_BUTTON);

        Map <String,Object> attributesToView = questService.processAttributes(pickedButton,userTo);

        attributesToView.forEach(req::setAttribute);
        UserTo userToActual = userService.getActualUserTo(userTo);
        addUserInfoToSession(req, userToActual);

        return getView();
    }

}
