package com.javarush.stepanov.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static com.javarush.stepanov.constants.ConstantsCommon.*;

public class QuizService {
    private final Map<String, String> allQuestionMap = new LinkedHashMap<>() ;
    Map<String, String> questionsMap = new LinkedHashMap<>();
    List<String> questionsList = new ArrayList<>();
    Map<String, String> wrongAnswers = new HashMap<>();
    String question;
    String answer;
    int step;

    public QuizService() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(TEXT_FILE);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            while (bufferedReader.ready()) {
                String line = bufferedReader.readLine();
                String[] split = line.split("\\|");
                if (split.length == 2) { // Проверяем, что строка содержит ровно один символ |
                    allQuestionMap.put(split[0].trim(), split[1].trim());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setStartCondition() {
        step = FIRST_STEP;
        questionsMap = getRandomQuestionMap();
        for (String question : questionsMap.keySet()) {
            questionsList.add(question);
        }
        question = questionsList.get(step);
    }

    public Map<String, String> getRandomQuestionMap() {
        List<String> keys = new ArrayList<>(allQuestionMap.keySet());
        Collections.shuffle(keys);
        for (int i = 0; i < NUMBER_OF_QUESTIONS; i++) {
            String key = keys.get(i);
            questionsMap.put(key, allQuestionMap.get(key));
        }
        return questionsMap;
    }

    public void clearRandomMap() {
        questionsMap.clear();
    }

    public Map<String, String> getRandomMapForTest() {
        return questionsMap;
    }

    public String getQuestion() {
        return question;
    }

    public int getStep() {
        return step;
    }

    public boolean quizIsNotEnding() {
        return(step != questionsMap.size());
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
        resultText.append("Верных ответов ");
        resultText.append(NUMBER_OF_QUESTIONS - wrongAnswers.size());
        resultText.append(" из  ");
        resultText.append(questionsList.size());
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
        return resultText;
    }

    public StringBuilder getFinalDescription(String userAnswer) {
        сheckingCorrectnessAnswer( userAnswer);
        String answer = questionsMap.get(question);
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
}
