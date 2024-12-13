package com.javarush.khmelov.cmd;

import com.javarush.khmelov.entity.QuestInfoEntity;
import com.javarush.khmelov.storage.quest.QuestRepository;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;

import static com.javarush.khmelov.storage.ConstantsCommon.LEFT;

@SuppressWarnings("unused")
public class GameQuest implements Command {

    private  List<QuestInfoEntity> questList ;
    private Map<String,String> winLossMap;
    private QuestInfoEntity conditionEntity;
    private Integer time;
    private Integer evidence;
    private Integer gold;
    private String pickedButton;
    int step;

    public GameQuest(QuestRepository questRepository) {
        this.winLossMap = questRepository.getWinLossMap();
        this.questList = questRepository.getAll();
    }

    @Override
    public String doGet(HttpServletRequest req) {

        //понимаю, что эта чать написана плохо, но беда со временем её исправлять
        String paramName = req.getParameter("pickedButton");
        if (paramName == null){
            setStartCondition(req);
        }
        else {
            if(step<questList.size()) {
                setCondition(req);
            }
        }

        if(step<questList.size()){
            fillRequest(req);
        }
        step++;

        if (lossCheck()){
            goToLoss(req);
        } else if (winCheck()) {
            goToWin(req);
        }

        return getView();
    }

    @Override
    public String doPost(HttpServletRequest req) {
        setCondition(req);
        fillRequest(req);
        return "start-page";
    }

    private void setStartCondition(HttpServletRequest req){
        pickedButton=LEFT;
        step=0;
        time = 5;
        evidence = 4;
        gold = 3;
        conditionEntity = questList.get(step);
    }

    private void setCondition(HttpServletRequest req){
        conditionEntity = questList.get(step);
        pickedButton = req.getParameter("pickedButton");
        time += conditionEntity.getDeltaTime(pickedButton) ;
        evidence += conditionEntity.getDeltaEvidence(pickedButton);
        gold += conditionEntity.getDeltaGold(pickedButton);
    }

    private void fillRequest(HttpServletRequest req){
        req.setAttribute("buttonLeft", conditionEntity.getButtonLeftText());
        req.setAttribute("buttonRight", conditionEntity.getButtonRightText());
        req.setAttribute("result", (pickedButton.equals(LEFT))
                ?conditionEntity.getResultLeftText()
                :conditionEntity.getResultRightText());
        req.setAttribute("resultRight", conditionEntity.getResultRightText());
        req.setAttribute("description", conditionEntity.getDescription());
        req.setAttribute("time", time);
        req.setAttribute("evidence", evidence);
        req.setAttribute("gold", gold);
        req.setAttribute("imageUrl", conditionEntity.getImageUrl());
    }

    private boolean winCheck(){
        if(step==6){
            return true;
        }
        return false;
    }

    private boolean lossCheck(){
        if((time<=0)||(evidence<=0)||(gold<=0)){
            return true;
        }
        return false;
    }

    private void goToWin(HttpServletRequest req){
        req.setAttribute("description", winLossMap.get("DESCRIPTION_TEXT_WIN"));
        req.setAttribute("imageUrl", winLossMap.get("IMAGE_URL_WIN"));
        req.setAttribute("isWin", true);
    }

    private void goToLoss(HttpServletRequest req){
        req.setAttribute("description", winLossMap.get("DESCRIPTION_TEXT_LOSS"));
        req.setAttribute("imageUrl", winLossMap.get("IMAGE_URL_LOSS"));
        req.setAttribute("isLoss", true);
    }




}
