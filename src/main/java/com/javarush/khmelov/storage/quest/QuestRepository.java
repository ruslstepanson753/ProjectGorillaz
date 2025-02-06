package com.javarush.khmelov.storage.quest;

import com.javarush.khmelov.entity.QuestInfoEntity;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.javarush.khmelov.storage.quest.ButtonText.*;
import static com.javarush.khmelov.storage.quest.Description.*;
import static com.javarush.khmelov.storage.quest.ImageUrl.*;
import static com.javarush.khmelov.storage.quest.ResultText.*;
import static com.javarush.khmelov.storage.quest.SetResourse.*;

public class QuestRepository {

    private final static List<QuestInfoEntity> questList = new LinkedList<>();
    private final static Map<String, String> questMap = new HashMap<>();

    public QuestRepository() {

    }

    public List<QuestInfoEntity> getQuestList() {
        return questList;
    }

    public Map<String, String> getQuestMap() {
        return questMap;
    }

}
