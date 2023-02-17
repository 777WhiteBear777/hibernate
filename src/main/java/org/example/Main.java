package org.example;

import org.example.connectivity.HibernateSession;
import org.example.dao.Impl.OrderHibernateDAO;
import org.example.dao.Impl.ShoppingCartHibernateDAO;
import org.example.dao.Impl.UserDetailsHibernateDAO;
import org.example.model.User;
import org.example.service.OrderService;
import org.hibernate.Session;

import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        User user;
        try(Session session = HibernateSession.getSessionFactory().openSession()){
            String hql = "from User u join u.products p  order by u.id desc limit 1";
            user = session.createQuery(hql, User.class).getSingleResult();
            System.out.println(session.find(User.class, user.getProducts()));
        }
//        OrderService orderService = new OrderService();
//        orderService.createOrderHibernate(1L);
//        ShoppingCartHibernateDAO shoppingCartHibernateDAO = new ShoppingCartHibernateDAO();
//        System.out.println( shoppingCartHibernateDAO.getAllProductByUser(1L));

    }
}