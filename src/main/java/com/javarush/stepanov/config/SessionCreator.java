package com.javarush.stepanov.config;

import com.javarush.stepanov.entity.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

import java.io.Closeable;
import java.util.concurrent.atomic.AtomicInteger;

import static com.javarush.stepanov.constants.ConstantsCommon.*;


@Slf4j
public class SessionCreator implements Closeable {

    private final SessionFactory sessionFactory;
    private final ThreadLocal<AtomicInteger> levelBox = new ThreadLocal<>();
    private final ThreadLocal<Session> sessionBox = new ThreadLocal<>();
    private static final String BROWN = "\u001B[38;5;94m";
    private static final String RESET = "\u001B[0m";

    @SneakyThrows
    public SessionCreator(ApplicationProperties applicationProperties) {
        Configuration configuration = new Configuration();
        configuration.addProperties(applicationProperties);
        configuration.addAnnotatedClass(UserTo.class);
        configuration.addAnnotatedClass(QuestInfoEntity.class);
        configuration.addAnnotatedClass(QuestMap.class);
        configuration.addAnnotatedClass(RouletteMap.class);
        configuration.addAnnotatedClass(Game.class);
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        sessionFactory = configuration.buildSessionFactory();
    }

    public Session getSession() {
        return sessionBox.get() == null || !sessionBox.get().isOpen()
                ? sessionFactory.openSession()
                : sessionBox.get();
    }


    public void beginTransactional() {
        if (levelBox.get() == null) {
            levelBox.set(new AtomicInteger(0));
        }
        AtomicInteger level = levelBox.get();
        if (level.getAndIncrement() == 0) {
            Session session = sessionFactory.openSession();
            sessionBox.set(session);
            session.beginTransaction();
        }
        log(level.get(), LOG_INFO_LIQUBESE_BEGIN_LEVEL);
    }

    public void endTransactional() {
        AtomicInteger level = levelBox.get();
        Session session = sessionBox.get();
        log(level.get(), LOG_INFO_LIQUBESE_END_LEVEL);
        if (level.decrementAndGet() == 0) {
            try {
                session.getTransaction().commit();
            } catch (RuntimeException e) {
                session.getTransaction().rollback();
                throw e;
            }
        }
    }

    private void log(int level, String message) {
        String simpleName = Thread.currentThread().getStackTrace()[4].toString();
       log.info(BROWN+"\t".repeat(level) + message + level + LOG_INFO_LIQUBESE_FROM + simpleName+RESET);
    }

    public void close() {
        sessionFactory.close();
    }

}
