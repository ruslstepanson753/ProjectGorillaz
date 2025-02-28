package com.javarush.stepanov.cmd;

import com.javarush.stepanov.BaseIT;
import com.javarush.stepanov.config.NanoSpring;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.javarush.stepanov.constants.ConstantsCommon.*;
import static org.mockito.Mockito.*;

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
        verify(req).setAttribute(eq(QUEST_ATTRIBUTE_BUTTON_LEFT), eq("Принять вызов"));
        verify(req).setAttribute(eq(QUEST_ATTRIBUTE_BUTTON_RIGHT), eq("Игнорировать вызов"));
        verify(req).setAttribute(eq(QUEST_ATTRIBUTE_RESULT), eq(null));
        verify(req).setAttribute(eq(QUEST_ATTRIBUTE_TIME), eq("4"));
    }

    @Test
    @DisplayName("when did wrong step then loss")
    void whenDidWrongStepThenLoss() {
        gameQuest.doGet(req);
        when(req.getParameter(ATTR_PICKED_BUTTON)).thenReturn(QUEST_BUTTON_RIGHT);
        gameQuest.doGet(req);
        verify(req).setAttribute(eq(QUEST_ATTRIBUTE_IS_LOSS), eq(true));
    }

    @Test
    @DisplayName("when did right steps then win")
    void whenDidRightStepsThenWin() {
        gameQuest.doGet(req);
        when(req.getParameter(ATTR_PICKED_BUTTON)).thenReturn(QUEST_BUTTON_LEFT);
        gameQuest.doGet(req);
        when(req.getParameter(ATTR_PICKED_BUTTON)).thenReturn(QUEST_BUTTON_RIGHT);
        gameQuest.doGet(req);
        when(req.getParameter(ATTR_PICKED_BUTTON)).thenReturn(QUEST_BUTTON_RIGHT);
        gameQuest.doGet(req);
        when(req.getParameter(ATTR_PICKED_BUTTON)).thenReturn(QUEST_BUTTON_RIGHT);
        gameQuest.doGet(req);
        when(req.getParameter(ATTR_PICKED_BUTTON)).thenReturn(QUEST_BUTTON_LEFT);
        gameQuest.doGet(req);
        verify(req).setAttribute(eq(QUEST_ATTRIBUTE_IS_WIN), eq(true));
    }
}
