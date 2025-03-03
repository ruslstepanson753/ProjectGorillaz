package com.javarush.stepanov.service;

import com.javarush.stepanov.constants.ConstantsCommon;
import com.javarush.stepanov.dto.UserTo;
import com.javarush.stepanov.exception.AppException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static com.javarush.stepanov.constants.ConstantsCommon.*;

public class QuizService extends GameService {
    private final Map<String, String> allQuestionMap = new LinkedHashMap<>();
    protected Map<String, String> questionsMap = new LinkedHashMap<>();
    protected List<String> questionsList = new ArrayList<>();
    protected Map<String, String> wrongAnswers = new HashMap<>();
    private String question;
    private String answer;
    private int step;

    public QuizService(UserService userService) {
        super(userService);
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(QUIZ_TEXT_FILE_NAME);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            while (bufferedReader.ready()) {
                String line = bufferedReader.readLine();
                String[] split = line.split("\\|");
                if (split.length == 2) {
                    allQuestionMap.put(split[0].trim(), split[1].trim());
                }
            }
        } catch (IOException e) {
            throw new AppException(ERROR_QUIZSERVICE_READ_FILE, e);
        }
    }


    @Override
    public Map<String, Object> processAttributes(String userAnswer, UserTo user) {
        Map<String, Object> attributesToView = new HashMap<>();

        if (userAnswer != null) {
            if (quizIsNotEnding()) {
                setCondition(userAnswer);
            } else {
                fillFinalViewAttributes(attributesToView,userAnswer,user);
                return attributesToView;
            }
        } else {
            setStartCondition();
        }
        fillViewAttributes(attributesToView);
        step++;
        return attributesToView;
    }

    private void setStartCondition() {
        step = QUIZ_FIRST_STEP;
        questionsMap = getRandomQuestionMap();
        for (String question : questionsMap.keySet()) {
            questionsList.add(question);
        }
        question = questionsList.get(step);
    }

    private void setCondition(String usersAnswer) {
        сheckingCorrectnessAnswer(usersAnswer);
        question = questionsList.get(step);
    }

    @Override
     protected void fillViewAttributes(Map<String, Object> attributesToView) {
        putParametrToMapIfNotNull(attributesToView, ConstantsCommon.ATTR_DESCRIPTION,question);
        putParametrToMapIfNotNull(attributesToView, QUIZ_ATTRIBUTE_QUESTION_NUMBER,step + 1);
    }

    private void fillFinalViewAttributes(Map<String, Object> attributesToView, String userAnswer, UserTo user) {
        StringBuilder resultText = getFinalDescription(userAnswer);
        putParametrToMapIfNotNull(attributesToView, ConstantsCommon.ATTR_DESCRIPTION,resultText.toString());
        putParametrToMapIfNotNull(attributesToView, QUIZ_ATTRIBUTE_QUESTION_NUMBER,step + 1);
        putParametrToMapIfNotNull(attributesToView, ATTR_IS_DONE,true);
        if(user!=null){
            if (isNullWrongAnswers()) {
                userService.addUserWin(user, GAME_QUIZ_NAME);
            } else {
                userService.addUserLoss(user, GAME_QUIZ_NAME);
            }
        }
        clearDataCash();

    }

    private Map<String, String> getRandomQuestionMap() {
        List<String> keys = new ArrayList<>(allQuestionMap.keySet());
        Collections.shuffle(keys);
        for (int i = 0; i < QUIZ_NUMBER_OF_QUESTIONS; i++) {
            String key = keys.get(i);
            questionsMap.put(key, allQuestionMap.get(key));
        }
        return questionsMap;
    }

    private void clearRandomMap() {
        questionsMap.clear();
    }

    private boolean quizIsNotEnding() {
        return (step != questionsMap.size());
    }

    private void сheckingCorrectnessAnswer(String usersAnswer) {
        answer = questionsMap.get(question);
        if (!usersAnswer.equalsIgnoreCase(answer)) {
            wrongAnswers.put(question, usersAnswer);
        }
    }

    private void clearDataCash() {
        questionsMap.clear();
        questionsList.clear();
        wrongAnswers.clear();
        clearRandomMap();
    }

    private StringBuilder getFinalDescription(String userAnswer) {
        сheckingCorrectnessAnswer(userAnswer);
        StringBuilder resultText = buildResultText();
        return resultText;
    }

    private StringBuilder buildResultText() {
        StringBuilder resultText = new StringBuilder();
        resultText.append(QUIZ_TRUE_ANSWERS);
        resultText.append(QUIZ_NUMBER_OF_QUESTIONS - wrongAnswers.size());
        resultText.append(QUIZ_OF_);
        resultText.append(questionsList.size());
        resultText.append(DOUBLE_NEXT_LINE);
        for (String question : wrongAnswers.keySet()) {
            resultText.append(QUIZ_FOR_ANSWER);
            resultText.append(question);
            resultText.append(NEXT_LINE);
            resultText.append(QUIZ_ENTERED_WRONG_ANSWER);
            resultText.append(wrongAnswers.get(question));
            resultText.append(NEXT_LINE);
            resultText.append(QUIZ_TRUE_ANSWER);
            resultText.append(questionsMap.get(question));
            resultText.append(DOUBLE_NEXT_LINE);
        }
        return resultText;
    }

    protected boolean isNullWrongAnswers() {
        boolean result = (wrongAnswers.size() == 0);
        return result;
    }

}
