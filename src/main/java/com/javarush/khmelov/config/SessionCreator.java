package com.javarush.khmelov.config;

import com.javarush.khmelov.entity.QuestInfoEntity;
import com.javarush.khmelov.entity.QuestMap;
import com.javarush.khmelov.entity.RouletteMap;
import com.javarush.khmelov.entity.User;
import com.javarush.khmelov.repository.QuestMapRepository;
import lombok.SneakyThrows;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

import java.util.HashMap;
import java.util.Map;


public class SessionCreator implements AutoCloseable {

    private final SessionFactory sessionFactory;

    @SneakyThrows
    public SessionCreator() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(QuestInfoEntity.class);
        configuration.addAnnotatedClass(QuestMap.class);
        configuration.addAnnotatedClass(RouletteMap.class);
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        sessionFactory = configuration.buildSessionFactory();
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }

    @Override
    public void close() {
        sessionFactory.close();
    }

}
