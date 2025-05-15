package com.starter.mugisha.services;

import com.starter.mugisha.dtos.CartItemDTO;
import com.starter.mugisha.models.Customer;
import com.starter.mugisha.models.Product;
import com.starter.mugisha.models.Purchased;
import com.starter.mugisha.models.ShoppingCartItem;
import com.starter.mugisha.repository.CustomerRepository;
import com.starter.mugisha.repository.ProductRepository;
import com.starter.mugisha.repository.PurchasedRepository;
import com.starter.mugisha.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartService {

    private final ShoppingCartRepository cartRepository;
    private final PurchasedRepository purchasedRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public ShoppingCartService(ShoppingCartRepository cartRepository, PurchasedRepository purchasedRepository, CustomerRepository customerRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.purchasedRepository = purchasedRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    /**
     * Adds an item to a customer's shopping cart. If it already exists, it updates the quantity.
     */
    public ShoppingCartItem addToCart(Customer customer, Product product, int quantity) {
        ShoppingCartItem existing = cartRepository.findByCustomerAndProduct(customer, product);

        if (existing != null) {
            existing.setQuantity(existing.getQuantity() + quantity);
            return cartRepository.save(existing);
        }

        ShoppingCartItem item = new ShoppingCartItem();
        item.setCustomer(customer);
        item.setProduct(product);
        item.setQuantity(quantity);
        return cartRepository.save(item);
    }

    /**
     * Returns all items currently in the customer's shopping cart.
     */
    public List<ShoppingCartItem> getCartItems(Customer customer) {
        return cartRepository.findAllByCustomer(customer);
    }

    /**
     * Processes the checkout: turns cart items into purchases and clears the cart.
     */
    public void checkout(Customer customer) {
        List<ShoppingCartItem> cartItems = cartRepository.findAllByCustomer(customer);

        for (ShoppingCartItem item : cartItems) {
            Purchased purchase = new Purchased();
            purchase.setCustomer(customer);
            purchase.setProduct(item.getProduct());
            purchase.setQuantity(item.getQuantity());
            purchase.setTotal(item.getProduct().getPrice() * item.getQuantity());
            purchase.setDate(LocalDate.now());

            purchasedRepository.save(purchase);
        }

        cartRepository.deleteAll(cartItems);
    }
    public void addItemsToCart(String email, List<CartItemDTO> items) {
        Customer customer = customerRepository.findByEmail(email).orElseThrow();

        for (CartItemDTO item : items) {
            Product product = productRepository.findById(item.getProductCode()).orElseThrow();
            ShoppingCartItem cartItem = new ShoppingCartItem();
            cartItem.setCustomer(customer);
            cartItem.setProduct(product);
            cartItem.setQuantity(item.getQuantity());
            cartRepository.save(cartItem);
        }
    }

    public List<ShoppingCartItem> getCustomerCart(String email) {
        Customer customer = customerRepository.findByEmail(email).orElseThrow();
        return cartRepository.findByCustomer(customer);
    }

    public void clearCart(Customer customer) {
        cartRepository.deleteAll(cartRepository.findByCustomer(customer));
    }
}