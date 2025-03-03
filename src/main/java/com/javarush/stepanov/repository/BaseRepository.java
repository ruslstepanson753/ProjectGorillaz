package com.javarush.stepanov.repository;

import com.javarush.stepanov.config.SessionCreator;
import com.javarush.stepanov.dto.UserTo;
import com.javarush.stepanov.entity.AbstractEntity;
import com.javarush.stepanov.entity.User;
import com.javarush.stepanov.exception.AppException;
import jakarta.persistence.*;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Stream;

import static com.javarush.stepanov.constants.ConstantsCommon.*;

@AllArgsConstructor
public class BaseRepository<E extends AbstractEntity> implements Repository<E> {

    private final SessionCreator sessionCreator;

    private final Class<E> entityClass;

    @Override
    public Collection<E> getAll() {
        Session session = sessionCreator.getSession();
        return session.createQuery("SELECT e FROM %s e".formatted(entityClass.getName()), entityClass).list();
    }

    @Override
    public Stream<E> find(E pattern) {
        try {
            Session session = sessionCreator.getSession();
            HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            JpaCriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(entityClass);
            Root<E> root = criteriaQuery.from(entityClass);
            Field[] fields = pattern.getClass().getDeclaredFields();
            List<Predicate> predicates = new ArrayList<>();
            for (Field field : fields) {
                if (field.trySetAccessible()) {
                    String name = field.getName();
                    Object value = field.get(pattern);
                    if (isPredacate(field, value)) {
                        Predicate predicate = criteriaBuilder.equal(root.get(name), value);
                        predicates.add(predicate);
                    }
                }
            }
            criteriaQuery.select(root);
            criteriaQuery.where(predicates.toArray(new Predicate[0]));
            Query<E> query = session.createQuery(criteriaQuery);
            List<E> list = query.list();
            return list.stream();
        } catch (IllegalAccessException e) {
            throw new AppException(ERROR_BASEREPOSITORY_FIND,e);
        }
    }

    private static boolean isPredacate(Field field, Object value) {
        return Objects.nonNull(value)
                && !field.isAnnotationPresent(Transient.class)
                && !field.isAnnotationPresent(OneToMany.class)
                && !field.isAnnotationPresent(ManyToOne.class)
                && !field.isAnnotationPresent(OneToOne.class)
                && !field.isAnnotationPresent(ManyToMany.class);
    }

    @Override
    public E get(long id) {
        Session session = sessionCreator.getSession();
        if (entityClass.equals(User.class)) {
            EntityGraph<?> entityGraph = session.getEntityGraph(User.GRAPH_USER_GAMES_FETCH);
            return (E) session.find(User.class, id, Map.of("javax.persistence.fetchgraph", entityGraph));
        }
        return session.find(entityClass, id);
    }

    @Override
    public void create(E entity) {
        Session session = sessionCreator.getSession();
        session.persist(entity);
    }

    @Override
    public void update(E entity) {
        Session session = sessionCreator.getSession();
        session.merge(entity);
    }

    @Override
    public void delete(E entity) {
        Session session = sessionCreator.getSession();
        session.remove(entity);
    }
}
