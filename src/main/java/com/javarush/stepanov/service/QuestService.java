package com.javarush.stepanov.service;

import com.javarush.stepanov.entity.QuestInfoEntity;
import com.javarush.stepanov.entity.QuestMap;
import com.javarush.stepanov.repository.QuestInfoEntityRepository;
import com.javarush.stepanov.repository.QuestMapRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestService {

    private final QuestMapRepository questMapRepository;

    private final QuestInfoEntityRepository questInfoEntityRepository;

    public QuestService(QuestMapRepository questMapRepository, QuestInfoEntityRepository questInfoEntityRepository) {
        this.questMapRepository = questMapRepository;
        this.questInfoEntityRepository = questInfoEntityRepository;
    }

    public List<QuestInfoEntity> getQuestList() {
        return (List<QuestInfoEntity>)questInfoEntityRepository.getAll();
    }

    public Map<String, String> getQuestMap() {
        Map<String,String> map = new HashMap<String,String>();
        List<QuestMap> questMapList = (List<QuestMap>)questMapRepository.getAll();
        questMapList.forEach(questMap -> {
            map.put(questMap.getKey(), questMap.getValue());
        });
        return map;
    }

}
