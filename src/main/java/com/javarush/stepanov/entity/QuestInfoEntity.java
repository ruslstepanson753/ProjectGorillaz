package com.javarush.stepanov.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Map;

//@Entity
//@Getter
//@Setter
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(name = "quest_info_entity")
//public class QuestInfoEntity implements AbstractEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "button_left_text", length = 256)
//    private String buttonLeftText;
//
//    @Column(name = "button_right_text", length = 256)
//    private String buttonRightText;
//
//    @Column(name = "result_left_text", length = 256)
//    private String resultLeftText;
//
//    @Column(name = "result_right_text", length = 256)
//    private String resultRightText;
//
//    @Column(columnDefinition = "jsonb")
//    @Convert(converter = MapToJsonConverter.class)
//    private Map<String, Integer> deltaTime;
//
//    @Column(columnDefinition = "jsonb")
//    @Convert(converter = MapToJsonConverter.class)
//    private Map<String, Integer> deltaEvidence;
//
//    @Column(columnDefinition = "jsonb")
//    @Convert(converter = MapToJsonConverter.class)
//    private Map<String, Integer> deltaGold;
//
//    @Column(length = 1024)
//    private String description;
//
//    @Column(name = "image_url", length = 64)
//    private String imageUrl;
//
//    public Integer getDeltaTime(String pickedButton) {
//        return deltaTime.get(pickedButton);
//    }
//
//    public Integer getDeltaEvidence(String pickedButton) {
//        return deltaEvidence.get(pickedButton);
//    }
//
//    public Integer getDeltaGold(String pickedButton) {
//        return deltaGold.get(pickedButton);
//    }
//}


