package com.javarush.khmelov.cmd;

import com.javarush.khmelov.service.UserService;
import com.javarush.khmelov.storage.quiz.QuizRepository;
import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class GameQuiz implements Command {
    UserService userService;
    QuizRepository quizRepository;
    Map <String,String> questionsMap;
    List<String> questions = new ArrayList<>();
    Map <String,String> wrongAnswers = new HashMap<>();
    String question;
    String answer;
    int step;

    public GameQuiz(UserService userService, QuizRepository quizRepository) {
        this.userService = userService;
        this.quizRepository = quizRepository;
    }

    @Override
    public String doGet(HttpServletRequest req) {
        String paramName = req.getParameter("pickedButton");
        if (paramName == null){
            setStartCondition(req);
        } else {
            getInfo(req);
        }
        if (step== questionsMap.size()){
            setFinalCondition(req);
        } else {
            setCondition(req);
        }
        step++;
        return getView();
    }

    private void setFinalCondition(HttpServletRequest req) {
        StringBuilder resultText = new StringBuilder();
        resultText.append("Верных ответов ");
        resultText.append(10-wrongAnswers.size());
        resultText.append("\n");
        resultText.append("\n");
        for (String question : wrongAnswers.keySet()) {
            resultText.append("На вопрос: ");
            resultText.append(question);
            resultText.append("\n");
            resultText.append("Получен неверный ответ: ");
            resultText.append(wrongAnswers.get(question));
            resultText.append("\n");
            resultText.append("Верный ответ: ");
            resultText.append(questionsMap.get(question));
            resultText.append("\n\n");
        }
        req.setAttribute("description", resultText.toString());
        req.setAttribute("isDone", true);
        if (wrongAnswers.size()==0){
            addUserWin(req,userService);
        } else {
            addUserLoss(req,userService);
        }
    }

    private void setStartCondition(HttpServletRequest req) {
        step = 0;
        questionsMap = quizRepository.getRandomQuestionMap();
        for(String question: questionsMap.keySet()){
            questions.add(question);
        }
        question = questions.get(step);
    }

    private void getInfo(HttpServletRequest req) {
        String usersAnswer = req.getParameter("answer");
        question = questions.get(step-1);
        answer = questionsMap.get(question);
        if (!usersAnswer.toLowerCase().equals(answer.toLowerCase())){
            wrongAnswers.put(question,usersAnswer);
        }
    }

    private void setCondition(HttpServletRequest req) {
        req.setAttribute("description", question);
        req.setAttribute("questionNumber", step+1);
    }
}
