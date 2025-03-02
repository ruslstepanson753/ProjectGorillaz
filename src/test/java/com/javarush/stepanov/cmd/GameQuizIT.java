package com.javarush.stepanov.cmd;

import com.javarush.stepanov.BaseIT;
import com.javarush.stepanov.config.NanoSpring;
import com.javarush.stepanov.service.QuizService;
import com.javarush.stepanov.service.UserService;
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
        verify(req).setAttribute(eq(QUIZ_ATTRIBUTE_QUESTION_NUMBER), eq(1));
    }

    @Test
    @DisplayName("when wrong answer then add wrong answer to wrongAnswersMap")
    void whenDidWrongStepThenLoss() {
        UserService userService = NanoSpring.find(UserService.class);
        QuizServiceTest quizServiceTest = new QuizServiceTest(userService);
        GameQuiz gameQuizTest = new GameQuiz(userService, quizServiceTest);

        gameQuizTest.doGet(req);
        when(req.getParameter(ATTR_PICKED_BUTTON)).thenReturn("1");
        when(req.getParameter(QUIZ_ATTRIBUTE_ANSWER)).thenReturn("1");
        gameQuizTest.doGet(req);
        when(req.getParameter(ATTR_PICKED_BUTTON)).thenReturn("2");
        when(req.getParameter(QUIZ_ATTRIBUTE_ANSWER)).thenReturn("2");
        gameQuizTest.doGet(req);
        when(req.getParameter(ATTR_PICKED_BUTTON)).thenReturn("3");
        when(req.getParameter(QUIZ_ATTRIBUTE_ANSWER)).thenReturn("3");
        gameQuizTest.doGet(req);
        when(req.getParameter(ATTR_PICKED_BUTTON)).thenReturn("4");
        when(req.getParameter(QUIZ_ATTRIBUTE_ANSWER)).thenReturn("4");
        gameQuizTest.doGet(req);

        Map<String,String> wrongAnswers = quizServiceTest.getWrongAnswersMapForTest();
        assertEquals(wrongAnswers.size(), 4);
        assertEquals(quizServiceTest.isNullWrongAnswers(), false);

        when(req.getParameter(ATTR_PICKED_BUTTON)).thenReturn("5");
        when(req.getParameter(QUIZ_ATTRIBUTE_ANSWER)).thenReturn("5");
        gameQuizTest.doGet(req);

        verify(req).setAttribute(eq(QUIZ_ATTRIBUTE_QUESTION_NUMBER), eq(5));
        verify(req).setAttribute(eq(ATTR_IS_DONE), eq(true));

    }

    @Test
    @DisplayName("when wrong answers no then no answer in wrongAnswersMap")
    void whenDidNotWrongStepThenWin() {
        UserService userService = NanoSpring.find(UserService.class);
        QuizServiceTest quizServiceTest = new QuizServiceTest(userService);
        GameQuiz gameQuizTest = new GameQuiz(userService, quizServiceTest);

        gameQuizTest.doGet(req);
        Map<String,String> trueAnswersMap = quizServiceTest.getQuestionsMapForTest();
        List<String> questionsForTest = quizServiceTest.getQuestionsListForTest();

        when(req.getParameter(ATTR_PICKED_BUTTON)).thenReturn("1");
        when(req.getParameter(QUIZ_ATTRIBUTE_ANSWER)).thenReturn(trueAnswersMap.get(questionsForTest.get(0)));
        gameQuizTest.doGet(req);
        when(req.getParameter(ATTR_PICKED_BUTTON)).thenReturn("2");
        when(req.getParameter(QUIZ_ATTRIBUTE_ANSWER)).thenReturn(trueAnswersMap.get(questionsForTest.get(1)));
        gameQuizTest.doGet(req);
        when(req.getParameter(ATTR_PICKED_BUTTON)).thenReturn("3");
        when(req.getParameter(QUIZ_ATTRIBUTE_ANSWER)).thenReturn(trueAnswersMap.get(questionsForTest.get(2)));
        gameQuizTest.doGet(req);
        when(req.getParameter(ATTR_PICKED_BUTTON)).thenReturn("4");
        when(req.getParameter(QUIZ_ATTRIBUTE_ANSWER)).thenReturn(trueAnswersMap.get(questionsForTest.get(3)));
        gameQuizTest.doGet(req);
        when(req.getParameter(ATTR_PICKED_BUTTON)).thenReturn("5");
        when(req.getParameter(QUIZ_ATTRIBUTE_ANSWER)).thenReturn(trueAnswersMap.get(questionsForTest.get(4)));


        assertEquals(quizServiceTest.isNullWrongAnswers(), true);
    }

}

class QuizServiceTest extends QuizService {

    public QuizServiceTest(UserService userService) {
        super(userService);
    }

    public  Map<String,String> getWrongAnswersMapForTest(){
        return wrongAnswers;
    }

    public Map<String,String> getQuestionsMapForTest(){
        return questionsMap;
    }

    public List<String> getQuestionsListForTest(){
        return questionsList;
    }

    public boolean isNullWrongAnswers() {
        boolean result = (wrongAnswers.size() == 0);
        return result;
    }

}