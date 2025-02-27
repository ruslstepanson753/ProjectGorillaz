package com.javarush.stepanov.cmd;

import com.javarush.stepanov.entity.User;
import com.javarush.stepanov.service.UserService;
import com.javarush.stepanov.service.QuizService;
import com.javarush.stepanov.util.ReqHelp;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

import static com.javarush.stepanov.constants.ConstantsCommon.*;

@SuppressWarnings("unused")
public class GameQuiz implements Command {
    UserService userService;
    QuizService quizService;


    public GameQuiz(UserService userService, QuizService quizService) {
        this.userService = userService;
        this.quizService = quizService;
    }

    @Override
    public String doGet(HttpServletRequest req) {
        User user = ReqHelp.getAttrFromSession(req, ATTR_USER);
        String pickedButton = req.getParameter(ATTR_PICKED_BUTTON);

        Map<String,Object> attributesToView = quizService.processAttributes(pickedButton,user);

        attributesToView.forEach(req::setAttribute);
        addUserInfoToSession(req, user);

        return getView();



    }



}
