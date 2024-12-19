package com.javarush.khmelov.cmd;

import com.javarush.khmelov.config.Winter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class GameRouletteTest extends AbstractTestClass{
    private GameRoulette gameRoulette;
    
    @BeforeEach
    void init(){
        gameRoulette = Winter.find(GameRoulette.class);        
    }

    @Test
    @DisplayName("when button not picked then start set")
    void whenButtonNotPickedThenStartSet() {
        fail("Not implemented");
    }

    @Test
    @DisplayName("when the bet matched then win")
    void whenTheBetMatchedThenWin() {
        fail("Not implemented");
    }

    @Test
    @DisplayName("when win then users win count rice")
    void whenWinThenUsersWinCountRice() {
        org.junit.jupiter.api.Assertions.fail("Not implemented");
    }
    
}