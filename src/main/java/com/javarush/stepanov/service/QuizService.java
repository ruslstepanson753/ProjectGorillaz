package com.javarush.stepanov.service;

import com.javarush.stepanov.exception.AppException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import static com.javarush.stepanov.constants.ConstantsCommon.*;

public class QuizService {
    private final Map<String, String> allQuestionMap = new LinkedHashMap<>();
    private Map<String, String> questionsMap = new LinkedHashMap<>();
    private List<String> questionsList = new ArrayList<>();
    private Map<String, String> wrongAnswers = new HashMap<>();
    private String question;
    private String answer;
    private int step;

    public QuizService() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(QUIZSERVICE_TEXT_FILE_NAME);
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

    public void setStartCondition() {
        step = QUIZSERVICE_FIRST_STEP;
        questionsMap = getRandomQuestionMap();
        for (String question : questionsMap.keySet()) {
            questionsList.add(question);
        }
        question = questionsList.get(step);
    }

    public Map<String, String> getRandomQuestionMap() {
        List<String> keys = new ArrayList<>(allQuestionMap.keySet());
        Collections.shuffle(keys);
        for (int i = 0; i < QUIZSERVICE_NUMBER_OF_QUESTIONS; i++) {
            String key = keys.get(i);
            questionsMap.put(key, allQuestionMap.get(key));
        }
        return questionsMap;
    }

    public void clearRandomMap() {
        questionsMap.clear();
    }

    public String getQuestion() {
        return question;
    }

    public int getStep() {
        return step;
    }

    public boolean quizIsNotEnding() {
        return (step != questionsMap.size());
    }

    public void setInfo(String usersAnswer) {
        сheckingCorrectnessAnswer(usersAnswer);
        question = questionsList.get(step);
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

    public StringBuilder buildResultText() {
        StringBuilder resultText = new StringBuilder();
        resultText.append(QUIZSERVICE_TRUE_ANSWERS);
        resultText.append(QUIZSERVICE_NUMBER_OF_QUESTIONS - wrongAnswers.size());
        resultText.append(QUIZSERVICE_OF_);
        resultText.append(questionsList.size());
        resultText.append(QUIZSERVICE_DOUBLE_NEXT_LINE);
        for (String question : wrongAnswers.keySet()) {
            resultText.append(QUIZSERVICE_FOR_ANSWER);
            resultText.append(question);
            resultText.append(QUIZSERVICE_NEXT_LINE);
            resultText.append(QUIZSERVICE_ENTERED_WRONG_ANSWER);
            resultText.append(wrongAnswers.get(question));
            resultText.append(QUIZSERVICE_NEXT_LINE);
            resultText.append(QUIZSERVICE_TRUE_ANSWER);
            resultText.append(questionsMap.get(question));
            resultText.append(QUIZSERVICE_DOUBLE_NEXT_LINE);
        }
        return resultText;
    }

    public StringBuilder getFinalDescription(String userAnswer) {
        сheckingCorrectnessAnswer(userAnswer);
        StringBuilder resultText = buildResultText();
        clearDataCash();
        return resultText;
    }

    public void nextStep() {
        step++;
    }

    public boolean isNullWrongAnswers() {
        return (wrongAnswers.size() == 0);
    }

    public Map<String, String> getWrongAnswersMapForTest() {
        return wrongAnswers;
    }

    public Map<String, String> getQuestionsMapForTest() {
       return questionsMap;
    }

    public List<String> getQuestionsListForTest() {
        return questionsList;
    }
}
