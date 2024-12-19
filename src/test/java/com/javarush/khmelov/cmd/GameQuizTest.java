package com.javarush.khmelov.cmd;

import com.javarush.khmelov.config.Winter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class GameQuizTest extends AbstractTestClass{
 private GameQuiz gameQuiz;

 @BeforeEach
    void setUp() {
     gameQuiz= Winter.find(GameQuiz.class);
 }

    @Test
    @DisplayName("when user answer is wrong then add wrong to list")
    void whenUserAnswerIsWrongThenAddWrongToList() {
        fail("Not implemented");
    }

    @Test
    @DisplayName("when step first then start condition set")
    void whenStepFirstThenStartConditionSet() {
        fail("Not implemented");
    }

    @Test
    @DisplayName("when step last then clear data cash")
    void whenStepLastThenClearDataCash() {
        fail("Not implemented");
    }

}