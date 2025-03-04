package ru.shaxowskiy.repositories;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import ru.shaxowskiy.models.Session;

import java.util.*;

@Repository
public class SessionRepository implements CrudRepository<Session, String>{
    private final SessionFactory sessionFactory;

    @Autowired
    public SessionRepository(@Lazy SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Session findById(String sessionId) {
        Transaction transaction = null;
        try(org.hibernate.Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
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
        Transaction transaction = null;
        try (org.hibernate.Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(sessionUser);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void update(Session entity, String s) {

    }

    @Override
    public void delete(String sessionId) {
        Transaction transaction = null;
        try(org.hibernate.Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM Session WHERE id = :sessionId");
            query.setParameter("sessionId", sessionId);
            System.out.println("УДАЛЕНА СЕССИЯ");
        }
    }
}