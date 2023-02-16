package org.example.dao;

import org.example.model.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderDAO {

    List<Order> getAllOrder();

    List<Order> getAllOrderByUser(Long userId);

    Long addOrder(Order order) throws SQLException;

}
