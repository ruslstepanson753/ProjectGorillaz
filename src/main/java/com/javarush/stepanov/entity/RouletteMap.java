package com.javarush.stepanov.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roulette_map")
public class RouletteMap implements AbstractEntity {
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


