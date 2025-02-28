package com.javarush.stepanov.service;

import com.javarush.stepanov.entity.QuestInfoEntity;
import com.javarush.stepanov.entity.QuestMap;
import com.javarush.stepanov.entity.User;
import com.javarush.stepanov.repository.QuestInfoEntityRepository;
import com.javarush.stepanov.repository.QuestMapRepository;
import com.javarush.stepanov.util.UrlHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static com.javarush.stepanov.constants.ConstantsCommon.*;
import static com.javarush.stepanov.constants.ConstantsCommon.QUEST_ATTRIBUTE_RESULT_LEFT;

public class QuestService extends GameService {
    private final QuestMapRepository questMapRepository;
    private final QuestInfoEntityRepository questInfoEntityRepository;
    private final List<QuestInfoEntity> questList;
    private final Map<String, String> questMap;
    private QuestInfoEntity conditionEntity;
    private Integer time;
    private Integer evidence;
    private Integer gold;
    private String pickedButton = QUEST_BUTTON_LEFT;
    private int step;

    public QuestService(QuestMapRepository questMapRepository, QuestInfoEntityRepository questInfoEntityRepository, UserService userService) {
        super(userService);
        this.questMapRepository = questMapRepository;
        this.questInfoEntityRepository = questInfoEntityRepository;
        questList = getQuestList();
        questMap = getQuestMap();
    }

    @Override
    public Map<String, Object> processAttributes(String pickedButton, User user) {
        if (pickedButton != null) {
            this.pickedButton = pickedButton;
        }
        Map<String, Object> attributesToView = new HashMap<>();

        if (pickedButton != null) {
            setCondition();
        } else {
            setStartCondition();
        }

        if (questIsNotEnding()) {
            fillViewAttributes(attributesToView);
        }

        step++;

        if (lossCheck()) {
            setLossInfo(attributesToView, user);
        } else if (winCheck()) {
            setWinInfo(attributesToView, user);
        }

        return attributesToView;
    }

    @Override
     void fillViewAttributes(Map<String, Object> attributesToView) {
        putParametrToMapIfNotNull(attributesToView, QUEST_ATTRIBUTE_BUTTON_LEFT, conditionEntity.getButtonLeftText());
        putParametrToMapIfNotNull(attributesToView, QUEST_ATTRIBUTE_BUTTON_RIGHT, conditionEntity.getButtonRightText());
        putParametrToMapIfNotNull(attributesToView, QUEST_ATTRIBUTE_RESULT_LEFT, conditionEntity.getResultLeftText());
        putParametrToMapIfNotNull(attributesToView, QUEST_ATTRIBUTE_RESULT_RIGHT, conditionEntity.getResultRightText());
        putParametrToMapIfNotNull(attributesToView, ATTR_DESCRIPTION, conditionEntity.getDescription());
        putParametrToMapIfNotNull(attributesToView, QUEST_ATTRIBUTE_TIME, time);
        putParametrToMapIfNotNull(attributesToView, QUEST_ATTRIBUTE_EVIDENCE, evidence);
        putParametrToMapIfNotNull(attributesToView, QUEST_ATTRIBUTE_GOLD, gold);
        putParametrToMapIfNotNull(attributesToView, ATTR_IMG_URL, getImgViewFromCondition());
        String result = ((pickedButton.equals(QUEST_BUTTON_LEFT))
                ? conditionEntity.getResultLeftText()
                : conditionEntity.getResultRightText());
        putParametrToMapIfNotNull(attributesToView, QUEST_ATTRIBUTE_RESULT, result);
        List.of(ATTR_IMG_EVIDENCE, ATTR_IMG_GOLD, ATTR_IMG_TIME)
                .forEach(attr -> attributesToView.put(attr, getImgViewFromMap(attr)));
    }

    private void setStartCondition() {
        step = Integer.parseInt(questMap.get(QUEST_MAP_START_STEP));
        time = Integer.parseInt(questMap.get(QUEST_MAP_START_TIME));
        evidence = Integer.parseInt(questMap.get(QUEST_MAP_START_EVIDENCE));
        gold = Integer.parseInt(questMap.get(QUEST_MAP_START_GOLD));
        conditionEntity = questList.get(step);
    }

    private void setCondition() {
        if (step < questList.size()) {
            conditionEntity = questList.get(step);
        }
        time += conditionEntity.getDeltaTime(pickedButton);
        evidence += conditionEntity.getDeltaEvidence(pickedButton);
        gold += conditionEntity.getDeltaGold(pickedButton);
    }

    private void setLossInfo(Map<String, Object> attributesToView, User user) {
        Map.ofEntries(
                Map.entry(QUEST_ATTRIBUTE_LOSS_CAUSE, getLossCause()),
                Map.entry(ATTR_DESCRIPTION, questMap.get(QUEST_MAP_DESCRIPTION_TEXT_LOSS)),
                Map.entry(ATTR_IMG_URL, getImgViewFromMap(QUEST_MAP_IMAGE_URL_LOSS)),
                Map.entry(QUEST_ATTRIBUTE_IS_LOSS, true)
        ).forEach(attributesToView::put);
        if (user!=null){
            userService.addUserLoss(user, GAME_QUEST_NAME);
        }
    }

    private void setWinInfo(Map<String, Object> attributesToView, User user) {
        Map.ofEntries(
                Map.entry(ATTR_DESCRIPTION, questMap.get(QUEST_MAP_DESCRIPTION_TEXT_WIN)),
                Map.entry(ATTR_IMG_URL, getImgViewFromMap(QUEST_MAP_IMAGE_URL_WIN)),
                Map.entry(QUEST_ATTRIBUTE_IS_WIN, true)
        ).forEach(attributesToView::put);
        if (user!=null){
            userService.addUserWin(user, GAME_QUEST_NAME);
        }
    }

    private List<QuestInfoEntity> getQuestList() {
        return (List<QuestInfoEntity>) questInfoEntityRepository.getAll();
    }

    private Map<String, String> getQuestMap() {
        Map<String, String> map = new HashMap<String, String>();
        List<QuestMap> questMapList = (List<QuestMap>) questMapRepository.getAll();
        questMapList.forEach(questMap -> {
            map.put(questMap.getKey(), questMap.getValue());
        });
        return map;
    }

    private boolean questIsNotEnding() {
        return (step < questList.size());
    }

    private String getImgViewFromMap(String imgKey) {
        String nameFile = questMap.get(imgKey);
        return UrlHelper.createUrlFromFileName(nameFile);
    }

    private String getImgViewFromCondition() {
        String nameFile = conditionEntity.getImageUrl();
        return UrlHelper.createUrlFromFileName(nameFile);
    }

    private boolean winCheck() {
        return step == QUEST_END_STEP;
    }

    private boolean lossCheck() {
        return (time <= QUEST_MIN_RESOURCE)
                || (evidence <= QUEST_MIN_RESOURCE)
                || (gold <= QUEST_MIN_RESOURCE)
                || ((step == QUEST_LOSS_STEP) & (pickedButton.equals(QUEST_BUTTON_RIGHT)));
    }

    private String getLossCause() {
        String key = Stream.of(
                        Map.entry(time == QUEST_MIN_RESOURCE, QUEST_MAP_CAUSE_TEXT_TIME_LOSS),
                        Map.entry(gold == QUEST_MIN_RESOURCE, QUEST_MAP_CAUSE_TEXT_GOLD_LOSS),
                        Map.entry(evidence == QUEST_MIN_RESOURCE, QUEST_MAP_CAUSE_TEXT_EVIDENCE_LOSS),
                        Map.entry(step == QUEST_LOSS_STEP && pickedButton.equals(QUEST_BUTTON_RIGHT), QUEST_MAP_CAUSE_TEXT_WRONG_STEP_LOSS)
                )
                .filter(Map.Entry::getKey)
                .findFirst()
                .map(Map.Entry::getValue)
                .orElse(questMap.get(QUEST_MAP_CAUSE_TEXT_UNKNOWN_LOSS));
        return questMap.get(key);
    }
}
