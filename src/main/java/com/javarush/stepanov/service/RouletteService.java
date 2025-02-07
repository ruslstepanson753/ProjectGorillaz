package com.javarush.stepanov.service;

import com.javarush.stepanov.entity.RouletteMap;
import com.javarush.stepanov.repository.RouletteMapRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.javarush.stepanov.constants.ConstantsCommon.*;

public class RouletteService {
    private final RouletteMapRepository rouletteMapRepository;
    private final Random random = new Random();

    public RouletteService(RouletteMapRepository rouletteMapRepository) {
        this.rouletteMapRepository = rouletteMapRepository;
    }

    public Map<String, String> getRoulletteMap() {
        Map<String,String> map = new HashMap<String,String>();
        List<RouletteMap> rouletteMapList = (List<RouletteMap>)rouletteMapRepository.getAll();
        rouletteMapList.forEach(questMap -> {
            map.put(questMap.getKey(), questMap.getValue());
        });
        return map;
    }

    public String getResultOfRotation() {
        int randomNumber = random.nextInt(37);
        if (randomNumber < 18) {
            return RED;
        }
        else if (randomNumber < 36) {
            return BLACK;
        }
        else {
            return ZERO;
        }
    }

}
