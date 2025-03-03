package com.javarush.stepanov.config;

//import com.javarush.stepanov.service.QuestService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Transactional
public class Config {

    private final LiqubaseInit liqubaseInit;

    public void fillEmptyRepository() {
        liqubaseInit.start();
    }
}
