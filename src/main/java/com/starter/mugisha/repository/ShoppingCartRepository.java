package com.starter.mugisha.repository;


import com.starter.mugisha.models.Customer;
import com.starter.mugisha.models.Product;
import com.starter.mugisha.models.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCartItem, Long> {
    ShoppingCartItem findByCustomerAndProduct(Customer customer, Product product);
    List<ShoppingCartItem> findAllByCustomer(Customer customer);
    List<ShoppingCartItem> findByCustomer(Customer customer);
}