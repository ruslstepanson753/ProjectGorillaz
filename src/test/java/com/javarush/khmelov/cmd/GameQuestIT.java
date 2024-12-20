package com.javarush.khmelov.cmd;

import com.javarush.khmelov.config.Winter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class GameQuestIT {
private GameQuest gameQuest;

@BeforeEach
    void setUp() {
    gameQuest = Winter.find(GameQuest.class);
}

    @Test
    @DisplayName("when step wrong then loss")
    void whenStepWrongThenLoss() {
        fail("Not implemented");
    }

    @Test
    @DisplayName("when resourse null then loss")
    void whenResourseNullThenLoss() {
        fail("Not implemented");
    }

    @Test
    @DisplayName("when resourse not null and last turn then win")
    void whenResourseNotNullAndLastTurnThenWin() {
        fail("Not implemented");
    }

}