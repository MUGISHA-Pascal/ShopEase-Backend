package com.starter.mugisha.services;

import com.starter.mugisha.dtos.CartItemDTO;
import com.starter.mugisha.models.Product;
import com.starter.mugisha.models.Purchased;
import com.starter.mugisha.models.ShoppingCartItem;
import com.starter.mugisha.models.User;
import com.starter.mugisha.repository.ProductRepository;
import com.starter.mugisha.repository.PurchasedRepository;
import com.starter.mugisha.repository.ShoppingCartRepository;
import com.starter.mugisha.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ShoppingCartService {

    private final ShoppingCartRepository cartRepository;
    private final PurchasedRepository purchasedRepository;
    @Autowired
    private UserRepository userRepository;
    private final ProductRepository productRepository;

    public ShoppingCartService(ShoppingCartRepository cartRepository, PurchasedRepository purchasedRepository,  ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.purchasedRepository = purchasedRepository;
        this.productRepository = productRepository;
    }

    /**
     * Adds an item to a customer's shopping cart. If it already exists, it updates the quantity.
     */
    public ShoppingCartItem addToCart(User user, Product product, int quantity) {
        ShoppingCartItem existing = cartRepository.findByUserAndProduct(user, product);

        if (existing != null) {
            existing.setQuantity(existing.getQuantity() + quantity);
            return cartRepository.save(existing);
        }

        ShoppingCartItem item = new ShoppingCartItem();
        item.setUser(user);
        item.setProduct(product);
        item.setQuantity(quantity);
        return cartRepository.save(item);
    }

    /**
     * Returns all items currently in the customer's shopping cart.
     */
    public List<ShoppingCartItem> getCartItems(User user) {
        return cartRepository.findAllByUser(user);
    }

    /**
     * Processes the checkout: turns cart items into purchases and clears the cart.
     */
    public void checkout(User user) {
        List<ShoppingCartItem> cartItems = cartRepository.findAllByUser(user);

        for (ShoppingCartItem item : cartItems) {
            Purchased purchase = new Purchased();
            purchase.setUser(user);
            purchase.setProduct(item.getProduct());
            purchase.setQuantity(item.getQuantity());
            purchase.setTotal(item.getProduct().getPrice() * item.getQuantity());
            purchase.setDate(LocalDate.now());

            purchasedRepository.save(purchase);
        }

        cartRepository.deleteAll(cartItems);
    }
    public void addItemsToCart(String email, List<CartItemDTO> items) {
        User user = userRepository.findByEmail(email).orElseThrow();

        for (CartItemDTO item : items) {
            Product product = productRepository.findById(item.getProductCode()).orElseThrow();
            ShoppingCartItem cartItem = new ShoppingCartItem();
            cartItem.setUser(user);
            cartItem.setProduct(product);
            cartItem.setQuantity(item.getQuantity());
            cartRepository.save(cartItem);
        }
    }

    public List<ShoppingCartItem> getCustomerCart(String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        return cartRepository.findAllByUser(user);
    }

    public void clearCart(User user) {
        cartRepository.deleteAll(cartRepository.findAllByUser(user));
    }
}