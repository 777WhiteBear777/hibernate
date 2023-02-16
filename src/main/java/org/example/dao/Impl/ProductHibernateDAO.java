package org.example.dao.Impl;

import org.example.connectivity.HibernateSession;
import org.example.dao.CommonDAO;
import org.example.model.Product;
import org.hibernate.Session;

import java.util.List;

public class ProductHibernateDAO implements CommonDAO<Product> {

    @Override
    public List<Product> getAll() {
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            return session.createQuery("from Product ", Product.class).list();
        }
    }

    @Override
    public Long addObj(Product product) {
        Long id;
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            session.persist(product);
            id = product.getId();
            session.getTransaction().commit();
        }

        return id;
    }

    @Override
    public Product getById(Long id) {
        Product product;
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            product = session.find(Product.class, id);
        }
        return product;
    }

    @Override
    public void update(Product product) {
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            session.merge(product);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Long id) {
        Product product;
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            product = session.find(Product.class, id);
            session.remove(product);
            session.getTransaction().commit();
        }
    }

}