package org.example.dao;

import org.example.model.Product;
import org.example.model.User;

import java.util.List;

public interface ShoppingCartDAO {
    List<Product> getAllProductByUser(Long userId);

    void deleteAllProductByUser(Long userId);

    void deleteShopCart(Long userId, Long productId);

    void addShopCart(Long userId, Long productId);
}
