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
    QuizService quizService;


    public GameQuiz(UserService userService, QuizService quizService) {
        this.quizService = quizService;
    }

    @Override
    public String doGet(HttpServletRequest req) {
        User user = ReqHelp.getAttrFromSession(req, ATTR_USER);
        String userAnswer = req.getParameter(QUIZ_ATTRIBUTE_ANSWER);

        Map<String,Object> attributesToView = quizService.processAttributes(userAnswer,user);

        attributesToView.forEach(req::setAttribute);
        addUserInfoToSession(req, user);

        return getView();
    }
}
