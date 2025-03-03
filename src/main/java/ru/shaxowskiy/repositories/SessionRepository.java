package ru.shaxowskiy.repositories;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import ru.shaxowskiy.models.Session;

import java.util.*;

@Slf4j
@Repository
public class SessionRepository implements CrudRepository<Session, String> {
    private final SessionFactory sessionFactory;

    @Autowired
    public SessionRepository(@Lazy SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Session findById(String sessionId) {
        try (org.hibernate.Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("FROM Session WHERE id = :sessionId");
            query.setParameter("sessionId", sessionId);
            return (Session) query.getSingleResult();
        }
    }

    @Override
    public List<Session> findAll() {
        return null;
    }

    @Override
    public void save(Session sessionUser) {
        try (org.hibernate.Session session = sessionFactory.openSession()) {
            session.save(sessionUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Session entity, String s) {

    }

    @Override
    public void delete(String sessionId) {
        org.hibernate.Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("DELETE FROM Session WHERE id = :sessionId");
        query.setParameter("sessionId", sessionId);
        query.executeUpdate();
        log.debug("Сессия пользователя удалена");
    }
}
