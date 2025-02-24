package ru.shaxowskiy.repositories;

import org.hibernate.Session;
import org.hibernate.SessionBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.shaxowskiy.models.Location;
import ru.shaxowskiy.models.User;

import java.util.List;

@Repository
public class LocationRepository implements CrudRepository<Location, Long> {
    private SessionFactory sessionFactory;

    @Autowired
    public LocationRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Location findById(Long aLong) {
        return null;
    }

    @Override
    public List<Location> findAll(){
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            return session.createQuery("FROM Location").getResultList();
        }
    }

    @Override
    public void save(Location location) {
        Transaction transaction = null;
        try (org.hibernate.Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            User user = location.getUser();
            session.save(user);
            session.save(location);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void update(Location entity, Long aLong) {

    }

    @Override
    public void delete(Long aLong) {

    }
}
