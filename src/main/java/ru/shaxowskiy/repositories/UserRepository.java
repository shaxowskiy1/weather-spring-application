package ru.shaxowskiy.repositories;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.shaxowskiy.models.User;

import java.util.List;

@Repository
public class UserRepository implements CrudRepository<User, Long> {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public User findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("FROM User WHERE id = :id", User.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        }
    }

    @Override
    public List<User> findAll() {
        try(Session session = sessionFactory.openSession()){
            Query<User> query = session.createQuery("FROM User", User.class);
            return query.getResultList();
        }
    }

    @Override
    public void save(User user) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public User findByUsername(String username) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("FROM User WHERE username = :name", User.class);
            query.setParameter("name", username);
            return query.uniqueResult();
        }
    }

    @Override
    public void update(User entity, Long aLong) {

    }

    @Override
    public void delete(Long aLong) {

    }
}
