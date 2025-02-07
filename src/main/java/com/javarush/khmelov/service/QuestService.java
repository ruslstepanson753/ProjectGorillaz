package com.javarush.khmelov.service;

import com.javarush.khmelov.entity.QuestInfoEntity;
import com.javarush.khmelov.entity.QuestMap;
import com.javarush.khmelov.repository.QuestInfoEntityRepository;
import com.javarush.khmelov.repository.QuestMapRepository;
import com.javarush.khmelov.repository.UserRepository;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
