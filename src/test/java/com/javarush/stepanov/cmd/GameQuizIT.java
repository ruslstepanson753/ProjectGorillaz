package com.javarush.stepanov.cmd;

import com.javarush.stepanov.BaseIT;
import com.javarush.stepanov.config.NanoSpring;
import com.javarush.stepanov.service.QuizService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static com.javarush.stepanov.constants.ConstantsCommon.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GameQuizIT extends BaseIT {

    private GameQuiz gameQuiz;

    @BeforeEach
    void setUp() {
        gameQuiz = NanoSpring.find(GameQuiz.class);
    }

    @Test
    @DisplayName("when start then init variable")
    void whenStartThenInitVariable() {
        String actualRedirect = gameQuiz.doGet(req);
        Assertions.assertEquals(actualRedirect, "game-quiz");
        verify(req).setAttribute(eq(GAME_QUIZ_ATTRIBUTE_QUESTION_NUMBER), eq(1));
    }

    @Test
    @DisplayName("when wrong answer then add wrong answer to wrongAnswersMap")
    void whenDidWrongStepThenLoss() {
        QuizService quizService = NanoSpring.find(QuizService.class);
        gameQuiz.doGet(req);
        when(req.getParameter(GAME_QUIZ_ATTRIBUTE_PICKED_BUTTON)).thenReturn("1");
        when(req.getParameter(GAME_QUIZ_ATTRIBUTE_ANSWER)).thenReturn("1");
        gameQuiz.doGet(req);
        when(req.getParameter(GAME_QUIZ_ATTRIBUTE_PICKED_BUTTON)).thenReturn("2");
        when(req.getParameter(GAME_QUIZ_ATTRIBUTE_ANSWER)).thenReturn("2");
        gameQuiz.doGet(req);
        when(req.getParameter(GAME_QUIZ_ATTRIBUTE_PICKED_BUTTON)).thenReturn("3");
        when(req.getParameter(GAME_QUIZ_ATTRIBUTE_ANSWER)).thenReturn("3");
        gameQuiz.doGet(req);
        when(req.getParameter(GAME_QUIZ_ATTRIBUTE_PICKED_BUTTON)).thenReturn("4");
        when(req.getParameter(GAME_QUIZ_ATTRIBUTE_ANSWER)).thenReturn("4");
        gameQuiz.doGet(req);

        Map<String,String> wrongAnswers = quizService.getWrongAnswersMapForTest();
        assertEquals(wrongAnswers.size(), 4);
        assertEquals(quizService.isNullWrongAnswers(), false);

        when(req.getParameter(GAME_QUIZ_ATTRIBUTE_PICKED_BUTTON)).thenReturn("5");
        when(req.getParameter(GAME_QUIZ_ATTRIBUTE_ANSWER)).thenReturn("5");
        gameQuiz.doGet(req);

        verify(req).setAttribute(eq(GAME_QUIZ_ATTRIBUTE_QUESTION_NUMBER), eq(5));
        verify(req).setAttribute(eq(GAME_QUIZ_ATTRIBUTE_IS_DONE), eq(true));

    }

    @Test
    @DisplayName("when wrong answers no then no answer in wrongAnswersMap")
    void whenDidNotWrongStepThenWin() {
        QuizService quizService = NanoSpring.find(QuizService.class);
        Map<String,String> trueAnswersMap = quizService.getQuestionsMapForTest();
        List<String> questionsForTest = quizService.getQuestionsListForTest();

        gameQuiz.doGet(req);
        when(req.getParameter(GAME_QUIZ_ATTRIBUTE_PICKED_BUTTON)).thenReturn("1");
        when(req.getParameter(GAME_QUIZ_ATTRIBUTE_ANSWER)).thenReturn(trueAnswersMap.get(questionsForTest.get(0)));
        gameQuiz.doGet(req);
        when(req.getParameter(GAME_QUIZ_ATTRIBUTE_PICKED_BUTTON)).thenReturn("2");
        when(req.getParameter(GAME_QUIZ_ATTRIBUTE_ANSWER)).thenReturn(trueAnswersMap.get(questionsForTest.get(1)));
        gameQuiz.doGet(req);
        when(req.getParameter(GAME_QUIZ_ATTRIBUTE_PICKED_BUTTON)).thenReturn("3");
        when(req.getParameter(GAME_QUIZ_ATTRIBUTE_ANSWER)).thenReturn(trueAnswersMap.get(questionsForTest.get(2)));
        gameQuiz.doGet(req);
        when(req.getParameter(GAME_QUIZ_ATTRIBUTE_PICKED_BUTTON)).thenReturn("4");
        when(req.getParameter(GAME_QUIZ_ATTRIBUTE_ANSWER)).thenReturn(trueAnswersMap.get(questionsForTest.get(3)));
        gameQuiz.doGet(req);

        assertEquals(quizService.isNullWrongAnswers(), true);
    }
}