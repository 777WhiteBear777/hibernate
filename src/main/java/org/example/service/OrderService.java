package org.example.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.connectivity.HibernateSession;
import org.example.dao.Impl.OrderHibernateDAO;
import org.example.dao.Impl.ProductHibernateDAO;
import org.example.dao.Impl.ShoppingCartHibernateDAO;
import org.example.dao.Impl.UserHibernateDAO;
import org.example.model.Order;
import org.example.model.Product;
import org.example.model.User;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class OrderService {
    private final Logger LOGGER = LogManager.getLogger(OrderService.class);

    public void createOrderHibernate(Long id) {

        Order order = new Order();
        UserHibernateDAO userHibernateDAO = new UserHibernateDAO();
        OrderHibernateDAO orderHibernateDAO = new OrderHibernateDAO();
        ShoppingCartHibernateDAO shoppingCartHibernateDAO = new ShoppingCartHibernateDAO();
        List<Product> productList;

        try {
            productList = shoppingCartHibernateDAO.getAllProductByUser(id);
            for (int i = 0; i < productList.size(); i++) {
                if (i != 0 && i != productList.size() - 1) {
                    order.setProduct(", " + productList.get(i).getName());
                    order.setTotalPrice(productList.get(i).getPrice());
                } else if (i == 0) {
                    order.setProduct(productList.get(i).getName());
                    order.setTotalPrice(productList.get(i).getPrice());
                } else {
                    order.setProduct(", " + productList.get(i).getName());
                    order.setTotalPrice(productList.get(i).getPrice());
                }

            }
            order.setUserId(userHibernateDAO.getById(id));
            orderHibernateDAO.addOrder(order);
            shoppingCartHibernateDAO.deleteAllProductByUser(id);
        } catch (SQLException e) {
            LOGGER.error(e);
        }

    }
}
