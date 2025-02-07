package com.javarush.stepanov.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "quest_map")
public class QuestMap implements AbstractEntity {
    @Id
    private String key;

    @Column(name = "value", length = 256)
    private String value;


    @Override
    public Long getId() {
        return 0L;
    }

    @Override
    public void setId(Long id) {

    }
}


