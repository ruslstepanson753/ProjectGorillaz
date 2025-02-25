package com.javarush.stepanov.cmd;

import com.javarush.stepanov.service.UserService;
import com.javarush.stepanov.service.QuizService;
import jakarta.servlet.http.HttpServletRequest;

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
        String paramName = req.getParameter("pickedButton");
        String usersAnswer = req.getParameter("answer");

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
        req.setAttribute("description", resultText.toString());
        int step = quizService.getStep();
        req.setAttribute("questionNumber", step + 1);
        req.setAttribute("isDone", true);
    }

    private void fillRequest(HttpServletRequest req) {
        String question = quizService.getQuestion();
        int step = quizService.getStep();
        req.setAttribute("description", question);
        req.setAttribute("questionNumber", step + 1);
    }

}
