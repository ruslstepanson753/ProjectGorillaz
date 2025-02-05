package com.javarush.khmelov.config;

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
        // configuration.configure();                             //2. hibernate.cfg.xml
        // Properties properties = configuration.getProperties(); //3.1 prepare for read
        // properties.load(SessionFactory.class.getResourceAsStream("/application.properties")); //3.2 your
        // configuration.addProperties(properties);               //3.3 application.properties
        // configuration.add????Resource()                        //and 100500 other ways
        configuration.addAnnotatedClass(User.class);
        //configuration.addAnnotatedClass(Quest.class);
        //configuration.addAnnotatedClass(Question.class);
        //configuration.addAnnotatedClass(Answer.class);
        //configuration.addAnnotatedClass(Game.class);
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

    public static void main(String[] args) {
        SessionCreator sessionCreator = new SessionCreator();
        try (sessionCreator){
            Session session = sessionCreator.getSession();
            Transaction tx = session.beginTransaction();
            try {
                User user = session.find(User.class, 1L);
                System.out.println(user);
                tx.commit();
            } catch (Exception e){
                tx.rollback();
            }
        }

    }
}
