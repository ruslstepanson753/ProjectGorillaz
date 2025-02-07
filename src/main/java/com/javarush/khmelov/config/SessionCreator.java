package com.javarush.khmelov.config;

import com.javarush.khmelov.entity.QuestInfoEntity;
import com.javarush.khmelov.entity.QuestMap;
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

    public static void createSession() {
        SessionCreator sessionCreator = new SessionCreator();
        try (sessionCreator){
            Session session = sessionCreator.getSession();
            Transaction tx = session.beginTransaction();
            try {
                Map<String,String> map = (Map<String, String>) NanoSpring.find(QuestMapRepository.class).getAll();
                Map<String,String> map2 = new HashMap<>();
                System.out.println("1");
                QuestMap questMap = NanoSpring.find(QuestMap.class);
                tx.commit();
            } catch (Exception e){
                e.printStackTrace();
                tx.rollback();
            }
        }
    }

}
