package com.javarush.stepanov.dto;

import com.javarush.stepanov.config.NanoSpring;
import com.javarush.stepanov.entity.Game;
import com.javarush.stepanov.entity.User;
import com.javarush.stepanov.repository.GameRepository;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Data
@Builder
public class UserTo {
     Long id;
     String login;
     String password;
     Collection<Game> games = new ArrayList<>();
}
