package org.example.dao.Impl;

import org.example.connectivity.HibernateSession;
import org.example.dao.CommonDAO;
import org.example.model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UserHibernateDAO implements CommonDAO<User> {

    @Override
    public List<User> getAll() {
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("from User ", User.class);
            return query.list();
        }
    }

    @Override
    public Long addObj(User user) {
        Long id;
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            session.persist(user);
            id = user.getId();
            session.getTransaction().commit();
            return id;
        }
    }

    @Override
    public User getById(Long id) {
        User user;
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            user = session.find(User.class, id);
        }
        return user;
    }

    @Override
    public void update(User user) {
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            session.merge(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Long id) {
        User user;
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            user = session.find(User.class, id);
            session.remove(session.contains(user) ? user : session.merge(user));
            session.getTransaction().commit();
        }
    }

}
