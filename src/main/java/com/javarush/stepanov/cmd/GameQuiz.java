package com.javarush.stepanov.cmd;

import com.javarush.stepanov.service.UserService;
import com.javarush.stepanov.service.QuizService;
import jakarta.servlet.http.HttpServletRequest;
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
        String paramName = req.getParameter(GAME_QUIZ_ATTRIBUTE_PICKED_BUTTON);
        String usersAnswer = req.getParameter(GAME_QUIZ_ATTRIBUTE_ANSWER);

        if (paramName == null) {
            quizService.setStartCondition();
            fillRequest(req);
        } else {
            if (quizService.quizIsNotEnding()) {
                quizService.setInfo(usersAnswer);
                fillRequest(req);
            } else {
                fillFinalRequest(req,usersAnswer);
                fillUserInfo(req);
            }
        }
        quizService.nextStep();
        return getView();
    }

    private void fillUserInfo(HttpServletRequest req) {
        if (quizService.isNullWrongAnswers()) {
            addUserWin(req, userService);
        } else {
            addUserLoss(req, userService);
        }
    }

    private void fillFinalRequest(HttpServletRequest req, String userAnswer) {
        StringBuilder resultText = quizService.getFinalDescription(userAnswer);
        req.setAttribute(GAME_QUIZ_ATTRIBUTE_DESCRIPTION, resultText.toString());
        int step = quizService.getStep();
        req.setAttribute(GAME_QUIZ_ATTRIBUTE_QUESTION_NUMBER, step + 1);
        req.setAttribute(GAME_QUIZ_ATTRIBUTE_IS_DONE, true);
    }

    private void fillRequest(HttpServletRequest req) {
        String question = quizService.getQuestion();
        int step = quizService.getStep();
        req.setAttribute(GAME_QUIZ_ATTRIBUTE_DESCRIPTION, question);
        req.setAttribute(GAME_QUIZ_ATTRIBUTE_QUESTION_NUMBER, step + 1);
    }

}
