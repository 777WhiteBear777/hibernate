package org.example;

import org.example.dao.Impl.OrderHibernateDAO;
import org.example.dao.Impl.ShoppingCartHibernateDAO;
import org.example.dao.Impl.UserDetailsHibernateDAO;

public class Main {
    public static void main(String[] args) {
        OrderHibernateDAO orderHibernateDAO = new OrderHibernateDAO();
        System.out.println(orderHibernateDAO.getAllOrderByUser(2L));

    }
}