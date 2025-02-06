package com.javarush.khmelov.config;

import com.javarush.khmelov.entity.QuestInfoEntity;
import com.javarush.khmelov.entity.User;
import lombok.SneakyThrows;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;


public class SessionCreator implements AutoCloseable {

    private final SessionFactory sessionFactory;

    @SneakyThrows
    public SessionCreator() {
        Configuration configuration = new Configuration();        //1. hibernate.properties
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(QuestInfoEntity.class);
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

    public static void newSessionCreator() {
        SessionCreator sessionCreator = new SessionCreator();
        try (sessionCreator){
            Session session = sessionCreator.getSession();
            Transaction tx = session.beginTransaction();
            try {
                QuestInfoEntity questInfo = session.find(QuestInfoEntity.class, 1L);
                System.out.println(questInfo);
                tx.commit();
            } catch (Exception e){
                e.printStackTrace();
                tx.rollback();
            }
        }

    }
}
