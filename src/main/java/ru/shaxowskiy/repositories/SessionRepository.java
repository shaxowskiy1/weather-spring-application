package ru.shaxowskiy.repositories;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.shaxowskiy.models.Session;

import javax.transaction.Transactional;
import java.util.*;

@Repository
public class SessionRepository implements CrudRepository<Session, Long>{
    private SessionFactory sessionFactory;

    @Autowired
    public SessionRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Session findById(Long aLong) {
        return null;
    }

    @Override
    public List<Session> findAll() {
        List<Session> list = new LinkedList<>();
        list.add(new Session());
        return list;
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
    public void update(Session entity, Long aLong) {

    }

    @Override
    public void delete(Long aLong) {

    }
}
