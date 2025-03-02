package com.javarush.stepanov.cmd;

import com.javarush.stepanov.BaseIT;
import com.javarush.stepanov.config.NanoSpring;
import com.javarush.stepanov.constants.ConstantsCommon;
import com.javarush.stepanov.repository.RouletteMapRepository;
import com.javarush.stepanov.service.RouletteService;
import com.javarush.stepanov.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.javarush.stepanov.constants.ConstantsCommon.*;
import static org.mockito.Mockito.*;

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
        verify(req).setAttribute(eq(ConstantsCommon.KEY_START_DESCRIPTION), eq("Добро пожаловать в Питерское казино. Делайте ставку!"));
        verify(req).setAttribute(eq(ConstantsCommon.KEY_RED_BUTTON_DESCRIPTION), eq("Ставлю на красное"));
        verify(req).setAttribute(eq(KEY_BLACK_BUTTON_DESCRIPTION), eq("Ставлю на чёрное"));
        verify(req).setAttribute(eq(ROULET_ZERO_BUTTON_DESCRIPTION), eq("Ставлю на зеро"));
        verify(req).setAttribute(eq(KEY_IMAGE_URL_START), eq("images\\roulette_start.jpg"));
    }

    @Test
    @DisplayName("when guessed right then win")
    void whenGuessedRightThenWin() {
        GameRoulette gameRouletteTest = getGameRouletteTest();
        gameRouletteTest.doGet(req);
        when(req.getParameter(ATTR_PICKED_BUTTON)).thenReturn(ROULET_RED);
        gameRouletteTest.doGet(req);
        verify(req).setAttribute(eq(ATTR_WIN_LOSS_DESCRIPTION), eq("Вы победили!"));
    }

    @Test
    @DisplayName("when not guessed right then loss")
    void whenNotGuessedRightThenLoss() {
        GameRoulette gameRouletteTest = getGameRouletteTest();
        gameRouletteTest.doGet(req);
        when(req.getParameter(ATTR_PICKED_BUTTON)).thenReturn(ROULET_BLACK);
        gameRouletteTest.doGet(req);
        verify(req).setAttribute(eq(ATTR_WIN_LOSS_DESCRIPTION), eq("Вы проиграли!"));
    }

    private GameRoulette getGameRouletteTest() {
        UserService userService = NanoSpring.find(UserService.class);
        RouletteMapRepository repository = NanoSpring.find(RouletteMapRepository.class);
        TestRouletteService testRouletteService = new TestRouletteService(repository);
        GameRoulette gameRouletteTest = new GameRoulette(userService, testRouletteService);
        return gameRouletteTest;
    }

    class TestRouletteService extends RouletteService {
        public TestRouletteService(RouletteMapRepository repository) {
            super(repository);
        }

        @Override
        public String getResultOfRotation() {
            return ROULET_RED;
        }
    }
}