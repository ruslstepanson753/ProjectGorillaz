package com.javarush.khmelov.cmd;

import com.javarush.khmelov.config.Winter;
import com.javarush.khmelov.storage.roulette.RouletteService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static com.javarush.khmelov.storage.ConstantsCommon.GO_START;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameRouletteIT extends AbstractTestClass{
    private GameRoulette gameRoulette;
    RouletteService rouletteService;

    @BeforeEach
    void init() {
        gameRoulette = Winter.find(GameRoulette.class);
        rouletteService = mock(RouletteService.class);
    }

    @Test
    @DisplayName("when the bet matched then win")
    void whenTheBetMatchedThenWin() {
        when(rouletteService.getResultOfRotation()).thenReturn("RED");
        when(req.getParameter("pickedButton")).thenReturn("RED");

        gameRoulette.doGet(req);

        Assertions.assertEquals("Вы победили!",req.getAttribute("winLossDescription"));
    }

    @Test
    @DisplayName("when win then users win count rice")
    void whenWinThenUsersWinCountRice() {
        org.junit.jupiter.api.Assertions.fail("Not implemented");
    }

}