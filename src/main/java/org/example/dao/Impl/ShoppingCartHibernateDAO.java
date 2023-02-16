package org.example.dao.Impl;

import org.example.connectivity.HibernateSession;
import org.example.dao.ShoppingCartDAO;
import org.example.model.Product;
import org.example.model.User;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import java.util.List;

public class ShoppingCartHibernateDAO implements ShoppingCartDAO {
    @Override
    public List<Product> getAllProductByUser(Long userId) {
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            String hql = "from Product p join p.users u where u.id = :id";
            List<Product> products = session.createQuery(hql, Product.class).setParameter("id", userId).list();
            products.forEach(product -> Hibernate.initialize(product.getUsers()));
            return products;
        }
    }

    @Override
    public void deleteAllProductByUser(Long userId) {
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            User user = session.get(User.class, userId);
            session.getTransaction().begin();
            user.getProducts().forEach(product -> product.getUsers().remove(user));
            session.getTransaction().commit();
        }
    }

    @Override
    public void deleteShopCart(Long userId, Long productId) {
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            User user = session.get(User.class, userId);
            Product product = session.get(Product.class, productId);
            session.getTransaction().begin();
            user.getProducts().remove(product);
            session.getTransaction().commit();
        }
    }

    @Override
    public void addShopCart(Long userId, Long productId) {
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            User user = session.get(User.class, userId);
            Product product = session.get(Product.class, productId);
            session.getTransaction().begin();
            user.getProducts().add(product);
            session.getTransaction().commit();
        }
    }
}
