package org.example.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.dao.Impl.OrderHibernateDAO;
import org.example.dao.Impl.ProductHibernateDAO;
import org.example.dao.Impl.ShoppingCartHibernateDAO;
import org.example.model.Order;
import org.example.model.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private final Logger LOGGER = LogManager.getLogger(OrderService.class);
    public void createOrderHibernate(Long id){

        Order order = new Order();
        OrderHibernateDAO orderHibernateDAO = new OrderHibernateDAO();
        ShoppingCartHibernateDAO shoppingCartHibernateDAO = new ShoppingCartHibernateDAO();
        ProductHibernateDAO productHibernateDAO = new ProductHibernateDAO();
        List<ShoppingCart> shopCartList;
        List<Product> productList = new ArrayList<>();

        try {
            shopCartList = shoppingCartHibernateDAO.getAllProductByUser(id);
            for (ShoppingCart shop : shopCartList
            ) {
                productList.add(productHibernateDAO.getById(shop.getProductId()));
            }

            for (int i = 0; i < productList.size(); i++) {
                if (i != 0 && i != productList.size() - 1) {
                    order.setProduct(", " + productList.get(i).getName());
                    order.setTotalPrice(productList.get(i).getPrice());
                }else if (i == 0) {
                    order.setProduct(productList.get(i).getName());
                    order.setTotalPrice(productList.get(i).getPrice());
                } else {
                    order.setProduct(", " + productList.get(i).getName());
                    order.setTotalPrice(productList.get(i).getPrice());
                }

            }
            order.setUserId(id);
            orderHibernateDAO.addOrder(order);
            shoppingCartHibernateDAO.deleteAllProductByUser(id);
        } catch (SQLException e) {
            LOGGER.error(e);
        }

    }
}
