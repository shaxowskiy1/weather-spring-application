package ru.shaxowskiy.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.shaxowskiy.models.Location;
import ru.shaxowskiy.models.User;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Repository
@Transactional
public class LocationRepositoryImpl implements LocationRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public LocationRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public Location findById(Long locationId) {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM Location WHERE id = :id");
        return (Location) query.setParameter("id", locationId).getSingleResult();
    }

    @Override
    @Transactional
    public List<Location> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Location").getResultList();
    }

    @Override
    @Transactional
    public void save(Location location) {
        Session session = sessionFactory.getCurrentSession();
        session.save(location);
    }

    @Override
    public void update(Location entity, Long aLong) {

    }

    @Override
    @Transactional
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("DELETE FROM Location where id = :id");
        query.setParameter("id", id).executeUpdate();
    }

    @Transactional
    public void delete(BigDecimal lat, BigDecimal lon) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("DELETE FROM Location WHERE latitude = :lat AND longitude = :lon");
        query.setParameter("lat", lat);
        query.setParameter("lon", lon);
        int result = query.executeUpdate();
        System.out.println("Deleted " + result + " locations.");
    }


    @Transactional
    public List<Location> findByUser(User user) {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM Location WHERE user.id = :id", Location.class);
        return query.setParameter("id", user.getId()).getResultList();
    }
}