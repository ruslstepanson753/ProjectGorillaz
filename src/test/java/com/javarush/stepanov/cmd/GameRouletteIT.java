package com.javarush.stepanov.cmd;

import com.javarush.stepanov.cmd.GameRoulette;
import com.javarush.stepanov.config.NanoSpring;
import com.javarush.stepanov.constants.ConstantsCommon;
import com.javarush.stepanov.repository.RouletteMapRepository;
import com.javarush.stepanov.service.RouletteService;
import com.javarush.stepanov.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import com.javarush.stepanov.BaseIT;

import static com.javarush.stepanov.constants.ConstantsCommon.*;

class GameRouletteIT extends BaseIT {

    GameRoulette gameRoulette;

    @BeforeEach
    void setUp() {
        gameRoulette = NanoSpring.find(GameRoulette.class);
    }

    @Test
    @DisplayName("when start then init variable")
    void whenStartThenInitVariable() {
        String actualRedirect = gameRoulette.doGet(req);
        Assertions.assertEquals(actualRedirect, GAME_ROULETTE_NAME);
        Mockito.verify(req).setAttribute(ArgumentMatchers.eq(ConstantsCommon.KEY_START_DESCRIPTION), ArgumentMatchers.eq("Добро пожаловать в Питерское казино. Делайте ставку!"));
        Mockito.verify(req).setAttribute(ArgumentMatchers.eq(ConstantsCommon.KEY_RED_BUTTON_DESCRIPTION), ArgumentMatchers.eq("Ставлю на красное"));
        Mockito.verify(req).setAttribute(ArgumentMatchers.eq(KEY_BLACK_BUTTON_DESCRIPTION), ArgumentMatchers.eq("Ставлю на чёрное"));
        Mockito.verify(req).setAttribute(ArgumentMatchers.eq(ROULET_ZERO_BUTTON_DESCRIPTION), ArgumentMatchers.eq("Ставлю на зеро"));
        Mockito.verify(req).setAttribute(ArgumentMatchers.eq(KEY_IMAGE_URL_START), ArgumentMatchers.eq("images\\roulette_start.jpg"));
    }

    @Test
    @DisplayName("when guessed right then win")
    void whenGuessedRightThenWin() {
        GameRoulette gameRouletteTest = getGameRouletteTest();
        gameRouletteTest.doGet(req);
        Mockito.when(req.getParameter(ATTR_PICKED_BUTTON)).thenReturn(ROULET_RED);
        gameRouletteTest.doGet(req);
        Mockito.verify(req).setAttribute(ArgumentMatchers.eq(ATTR_WIN_LOSS_DESCRIPTION), ArgumentMatchers.eq("Вы победили!"));
    }

    @Test
    @DisplayName("when not guessed right then loss")
    void whenNotGuessedRightThenLoss() {
        GameRoulette gameRouletteTest = getGameRouletteTest();
        gameRouletteTest.doGet(req);
        Mockito.when(req.getParameter(ATTR_PICKED_BUTTON)).thenReturn(ROULET_BLACK);
        gameRouletteTest.doGet(req);
        Mockito.verify(req).setAttribute(ArgumentMatchers.eq(ATTR_WIN_LOSS_DESCRIPTION), ArgumentMatchers.eq("Вы проиграли!"));
    }

    private GameRoulette getGameRouletteTest() {
        UserService userService = NanoSpring.find(UserService.class);
        RouletteMapRepository repository = NanoSpring.find(RouletteMapRepository.class);
        TestRouletteService testRouletteService = new TestRouletteService(repository, userService);
        GameRoulette gameRouletteTest = new GameRoulette(userService, testRouletteService);
        return gameRouletteTest;
    }

    class TestRouletteService extends RouletteService {
        public TestRouletteService(RouletteMapRepository rouletteMapRepository,UserService userService) {
            super(rouletteMapRepository, userService);
        }

        @Override
        public String getResultOfRotation() {
            return ROULET_RED;
        }
    }
}