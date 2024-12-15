package com.javarush.khmelov.cmd;

import com.javarush.khmelov.entity.QuestInfoEntity;
import com.javarush.khmelov.entity.User;
import com.javarush.khmelov.service.UserService;
import com.javarush.khmelov.storage.quest.QuestRepository;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;

import static com.javarush.khmelov.storage.ConstantsCommon.LEFT;
import static com.javarush.khmelov.storage.ConstantsCommon.RIGHT;

@SuppressWarnings("unused")
public class GameQuest implements Command {
    private final UserService userService;
    private  List<QuestInfoEntity> questList ;
    private Map<String,String> questMap;
    private QuestInfoEntity conditionEntity;
    private Integer time;
    private Integer evidence;
    private Integer gold;
    private String pickedButton =LEFT;
    int step;

    public GameQuest(QuestRepository questRepository, UserService userService) {
        this.userService = userService;
        this.questMap = questRepository.getQuestMap();
        this.questList = questRepository.getQuestList();
    }

    @Override
    public String doGet(HttpServletRequest req) {

        String paramName = req.getParameter("pickedButton");
        if (paramName == null){
            setStartCondition(req);
        }
        else {
                setCondition(req);
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
        step=Integer.parseInt(questMap.get("START_STEP"));
        time = Integer.parseInt(questMap.get("START_TIME"));
        evidence = Integer.parseInt(questMap.get("START_EVIDENCE"));
        gold = Integer.parseInt(questMap.get("START_GOLD"));
        conditionEntity = questList.get(step);
        req.getSession().setAttribute("IMAGE_URL_EVIDENCE",questMap.get("IMAGE_URL_EVIDENCE"));
        req.getSession().setAttribute("IMAGE_URL_GOLD",questMap.get("IMAGE_URL_GOLD"));
        req.getSession().setAttribute("IMAGE_URL_TIME",questMap.get("IMAGE_URL_TIME"));
    }

    private void setCondition(HttpServletRequest req){
        if(step<questList.size()){
            conditionEntity = questList.get(step);
        }
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
        if((time<=0)||(evidence<=0)||(gold<=0)||((step==2)&(pickedButton.equals(RIGHT)))){
            return true;
        }
        return false;
    }

    private void goToWin(HttpServletRequest req){
        req.setAttribute("description", questMap.get("DESCRIPTION_TEXT_WIN"));
        req.setAttribute("imageUrl", questMap.get("IMAGE_URL_WIN"));
        req.setAttribute("isWin", true);
        User user = findUser(req,userService);
        if (user != null) {
            user.setGamesCount(user.getGamesCount()+1);
            user.setWinsCount(user.getWinsCount()+1);
            addUserInfoToSession(req, user);
        }
    }

    private void goToLoss(HttpServletRequest req){
        String lossСause;
        if (time == 0) {
            lossСause = questMap.get("CAUSE_TEXT_TIME_LOSS");
        } else if (gold == 0) {
            lossСause = questMap.get("CAUSE_TEXT_GOLD_LOSS");
        } else if (evidence == 0) {
            lossСause = questMap.get("CAUSE_TEXT_EVIDENCE_LOSS");
        } else if (step == 2 && pickedButton.equals(RIGHT)) {
            lossСause = questMap.get("CAUSE_TEXT_WRONG_STEP_LOSS");
        } else {
            lossСause = questMap.get("CAUSE_TEXT_UNKNOWN_LOSS");
        }
        req.setAttribute("lossСause", lossСause);
        req.setAttribute("description", questMap.get("DESCRIPTION_TEXT_LOSS"));
        req.setAttribute("imageUrl", questMap.get("IMAGE_URL_LOSS"));
        req.setAttribute("isLoss", true);
        User user = findUser(req,userService);
        if (user != null) {
            user.setGamesCount(user.getGamesCount()+1);
            user.setLossCount(user.getLossCount()+1);
            addUserInfoToSession(req, user);
        }

    }




}
