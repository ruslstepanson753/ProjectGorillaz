package com.javarush.stepanov.service;

import com.javarush.stepanov.entity.QuestInfoEntity;
import com.javarush.stepanov.entity.QuestMap;
import com.javarush.stepanov.repository.QuestInfoEntityRepository;
import com.javarush.stepanov.repository.QuestMapRepository;
import com.javarush.stepanov.util.UrlHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.javarush.stepanov.constants.ConstantsCommon.*;
import static com.javarush.stepanov.constants.ConstantsCommon.RIGHT;

public class QuestService {

    private final QuestMapRepository questMapRepository;
    private final QuestInfoEntityRepository questInfoEntityRepository;

    private final List<QuestInfoEntity> questList;
    private final Map<String, String> questMap;
    private QuestInfoEntity conditionEntity;
    private Integer time;
    private Integer evidence;
    private Integer gold;
    private String pickedButton = LEFT;
    private int step;

    public QuestService(QuestMapRepository questMapRepository, QuestInfoEntityRepository questInfoEntityRepository) {
        this.questMapRepository = questMapRepository;
        this.questInfoEntityRepository = questInfoEntityRepository;
        questList = getQuestList();
        questMap = getQuestMap();
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

    public String[] getWinViewInfo() {
        String[] winViewInfo = new String[2];
        winViewInfo[0] = questMap.get("DESCRIPTION_TEXT_WIN");
        winViewInfo[1] =  getImgViewFromMap("IMAGE_URL_WIN");
        return winViewInfo;
    }

    public String[] getLossViewInfo() {
        String[] lossViewInfo = new String[3];
        lossViewInfo[0] = getLossCause();
        lossViewInfo[1] = questMap.get("DESCRIPTION_TEXT_LOSS");
        lossViewInfo[2] =  getImgViewFromMap("IMAGE_URL_LOSS");
        return lossViewInfo;
    }

    public String[] getViewInfo() {
        String[] viewInfo = new String[9];
        viewInfo[0] = conditionEntity.getButtonLeftText();
        viewInfo[1] = conditionEntity.getButtonRightText();
        viewInfo[2] = (pickedButton.equals(LEFT))
                ? conditionEntity.getResultLeftText()
                : conditionEntity.getResultRightText();
        viewInfo[3] = conditionEntity.getResultRightText();
        viewInfo[4] =  conditionEntity.getDescription();
        viewInfo[5] =  time.toString();
        viewInfo[6] =  evidence.toString();
        viewInfo[7] =  gold.toString();
        viewInfo[8] =  getImgViewFromCondition();
        return viewInfo;
    }

    public boolean isNotEnding(){
        return (step < questList.size());
    }

    public void setStartCondition() {
        step = Integer.parseInt(questMap.get("START_STEP"));
        time = Integer.parseInt(questMap.get("START_TIME"));
        evidence = Integer.parseInt(questMap.get("START_EVIDENCE"));
        gold = Integer.parseInt(questMap.get("START_GOLD"));
        conditionEntity = questList.get(step);
       
    }
    
    public String[] getStartViewInfo(){
        String[] startViews = new String[3];
        startViews[0] = getImgViewFromMap("IMAGE_URL_EVIDENCE");
        startViews[1] = getImgViewFromMap("IMAGE_URL_GOLD");
        startViews[2] = getImgViewFromMap("IMAGE_URL_TIME");
        return startViews;
    }

    private String getImgViewFromMap(String imgKey){
        String nameFile =  questMap.get(imgKey);
        return UrlHelper.createUrlFromFileName(nameFile);
    }

    private String getImgViewFromCondition(){
        String nameFile =  conditionEntity.getImageUrl();
        return UrlHelper.createUrlFromFileName(nameFile);
    }

    public void setCondition(String pickedButton) {
        if (step < questList.size()) {
            conditionEntity = questList.get(step);
        }
        time += conditionEntity.getDeltaTime(pickedButton);
        evidence += conditionEntity.getDeltaEvidence(pickedButton);
        gold += conditionEntity.getDeltaGold(pickedButton);
        this.pickedButton = pickedButton;
    }

    public void goNextStep() {
        step++;
    }

    public boolean winCheck() {
        return step == QUEST_END_STEP;
    }

    public boolean lossCheck() {
        return (time <= QUEST_MIN_RESOURCE)
                || (evidence <= QUEST_MIN_RESOURCE)
                || (gold <= QUEST_MIN_RESOURCE)
                || ((step == QUEST_LOSS_STEP) & (pickedButton.equals(RIGHT)));
    }

    private String getLossCause() {
        String lossСause;
        if (time == QUEST_MIN_RESOURCE) {
            lossСause = questMap.get("CAUSE_TEXT_TIME_LOSS");
        } else if (gold == QUEST_MIN_RESOURCE) {
            lossСause = questMap.get("CAUSE_TEXT_GOLD_LOSS");
        } else if (evidence == QUEST_MIN_RESOURCE) {
            lossСause = questMap.get("CAUSE_TEXT_EVIDENCE_LOSS");
        } else if (step == QUEST_LOSS_STEP && pickedButton.equals(RIGHT)) {
            lossСause = questMap.get("CAUSE_TEXT_WRONG_STEP_LOSS");
        } else {
            lossСause = questMap.get("CAUSE_TEXT_UNKNOWN_LOSS");
        }
        return lossСause;
    }
}
