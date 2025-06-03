package com.starter.mugisha.repository;


import com.starter.mugisha.models.Product;
import com.starter.mugisha.models.ShoppingCartItem;
import com.starter.mugisha.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCartItem, Long> {
    ShoppingCartItem findByUserAndProduct(User user, Product product);
    List<ShoppingCartItem> findAllByUser(User user);
    List<ShoppingCartItem> findByUser(User user);
}