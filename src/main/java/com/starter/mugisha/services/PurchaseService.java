package com.starter.mugisha.services;

import com.starter.mugisha.models.*;
import com.starter.mugisha.repository.*;
import com.starter.mugisha.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PurchaseService {
    @Autowired private PurchasedRepository purchasedRepo;
    @Autowired private ProductRepository productRepo;
    @Autowired private ShoppingCartService cartService;
    @Autowired private UserRepository userRepository;

    public List<PurchasedDTO> checkout(CheckoutDTO dto) {
        User  user = userRepository.findByEmail(dto.getCustomerEmail()).orElseThrow();
        List<PurchasedDTO> purchases = new ArrayList<>();

        for (CartItemDTO item : dto.getItems()) {
            Product product = productRepo.findById(item.getProductCode()).orElseThrow();
            Purchased purchase = new Purchased();
            purchase.setProduct(product);
            purchase.setUser(user);
            purchase.setQuantity(item.getQuantity());
            purchase.setDate(LocalDate.now());
            purchasedRepo.save(purchase);

            PurchasedDTO pdto = new PurchasedDTO();
            pdto.setCustomerId(user.getId());
            pdto.setProductCode(product.getCode());
            pdto.setQuantity(item.getQuantity());
            pdto.setTotal(product.getPrice() * item.getQuantity());
            purchases.add(pdto);
        }

        cartService.clearCart(user);
        return purchases;
    }

    public List<Purchased> getAllPurchases() {
        return purchasedRepo.findAll();
    }
}
