package com.javarush.stepanov.cmd;

import com.javarush.stepanov.config.NanoSpring;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import com.javarush.stepanov.BaseIT;

import static com.javarush.stepanov.constants.ConstantsCommon.*;

class GameQuestIT extends BaseIT {
    private GameQuest gameQuest;

    @BeforeEach
    void init() {
        gameQuest = NanoSpring.find(GameQuest.class);
    }

    @Test
    @DisplayName("when start then init variable")
    void whenStartThenInitVariable() {
        String actualRedirect = gameQuest.doGet(req);
        Assertions.assertEquals(actualRedirect, "game-quest");
        Mockito.verify(req).setAttribute(ArgumentMatchers.eq(QUEST_ATTRIBUTE_BUTTON_LEFT), ArgumentMatchers.eq("Принять вызов"));
        Mockito.verify(req).setAttribute(ArgumentMatchers.eq(QUEST_ATTRIBUTE_BUTTON_RIGHT), ArgumentMatchers.eq("Игнорировать вызов"));
        Mockito.verify(req).setAttribute(ArgumentMatchers.eq(ATTR_IMG_EVIDENCE), ArgumentMatchers.eq("images\\quest_evidence.jpg"));
        Mockito.verify(req).setAttribute(ArgumentMatchers.eq(QUEST_ATTRIBUTE_TIME), ArgumentMatchers.eq(4));
    }

    @Test
    @DisplayName("when did wrong step then loss")
    void whenDidWrongStepThenLoss() {
        gameQuest.doGet(req);
        Mockito.when(req.getParameter(ATTR_PICKED_BUTTON)).thenReturn(QUEST_BUTTON_RIGHT);
        gameQuest.doGet(req);
        Mockito.verify(req).setAttribute(ArgumentMatchers.eq(QUEST_ATTRIBUTE_IS_LOSS), ArgumentMatchers.eq(true));
    }

    @Test
    @DisplayName("when did right steps then win")
    void whenDidRightStepsThenWin() {
        gameQuest.doGet(req);
        Mockito.when(req.getParameter(ATTR_PICKED_BUTTON)).thenReturn(QUEST_BUTTON_LEFT);
        gameQuest.doGet(req);
        Mockito.when(req.getParameter(ATTR_PICKED_BUTTON)).thenReturn(QUEST_BUTTON_RIGHT);
        gameQuest.doGet(req);
        Mockito.when(req.getParameter(ATTR_PICKED_BUTTON)).thenReturn(QUEST_BUTTON_RIGHT);
        gameQuest.doGet(req);
        Mockito.when(req.getParameter(ATTR_PICKED_BUTTON)).thenReturn(QUEST_BUTTON_RIGHT);
        gameQuest.doGet(req);
        Mockito.when(req.getParameter(ATTR_PICKED_BUTTON)).thenReturn(QUEST_BUTTON_LEFT);
        gameQuest.doGet(req);
        Mockito.verify(req).setAttribute(ArgumentMatchers.eq(QUEST_ATTRIBUTE_IS_WIN), ArgumentMatchers.eq(true));
    }
}
