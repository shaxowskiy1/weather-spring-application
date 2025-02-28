package ru.shaxowskiy.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.shaxowskiy.models.Location;
import ru.shaxowskiy.models.User;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class LocationRepository implements CrudRepository<Location, Long> {
    private SessionFactory sessionFactory;

    @Autowired
    public LocationRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Location findById(Long locationId) {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Location WHERE id = :id");
            return (Location) query.setParameter("id", locationId).getSingleResult();
        }
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
    public void delete(Long id) {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            Query query = session.createQuery("DELETE Location where id = :id", Location.class);
            query.setParameter("id", id).executeUpdate();
            transaction.commit();
        }
        catch (Exception e) {
            transaction.rollback();
        }
    }

    public void delete(BigDecimal lat, BigDecimal lon) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {

            Query query = session.createQuery("DELETE FROM Location WHERE latitude = :lat AND longitude = :lon");
            query.setParameter("lat", lat);
            query.setParameter("lon", lon);
            transaction = session.beginTransaction();
            int result = query.executeUpdate();
            transaction.commit();
            System.out.println("Deleted " + result + " locations.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }



    public List<Location> findByUser(User user) {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Location WHERE user.id = :id", Location.class);
            return query.setParameter("id", user.getId()).getResultList();
        }

    }
}
