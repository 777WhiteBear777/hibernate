package org.example.connectivity;

import org.example.model.Order;
import org.example.model.Product;
import org.example.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.UserDetails;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSession {
    private static SessionFactory sessionFactory;
    private final static Logger LOGGER = LogManager.getLogger(HibernateSession.class);

    private HibernateSession() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                sessionFactory = new Configuration().
                        addAnnotatedClass(User.class).
                        addAnnotatedClass(Product.class).
                        addAnnotatedClass(UserDetails.class).
                        addAnnotatedClass(Order.class).
                        buildSessionFactory();
            } catch (Exception e) {
                LOGGER.error(e);
            }
        }
        return sessionFactory;
    }
}
