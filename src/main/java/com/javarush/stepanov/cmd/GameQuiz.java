package com.javarush.stepanov.cmd;

import com.javarush.stepanov.dto.UserTo;
import com.javarush.stepanov.service.UserService;
import com.javarush.stepanov.service.QuizService;
import com.javarush.stepanov.util.ReqHelp;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

import static com.javarush.stepanov.constants.ConstantsCommon.*;

@SuppressWarnings("unused")
public class GameQuiz implements Command {
    QuizService quizService;
    UserService userService;

    public GameQuiz(UserService userService, QuizService quizService) {
        this.userService = userService;
        this.quizService = quizService;
    }

    @Override
    public String doGet(HttpServletRequest req) {
        UserTo userTo = ReqHelp.getAttrFromSession(req, ATTR_USER);
        String userAnswer = req.getParameter(QUIZ_ATTRIBUTE_ANSWER);

        Map<String,Object> attributesToView = quizService.processAttributes(userAnswer,userTo);

        attributesToView.forEach(req::setAttribute);
        UserTo actualUserTo = userService.getActualUserTo(userTo);
        addUserInfoToSession(req, actualUserTo);

        return getView();
    }
}
