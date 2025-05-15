package com.starter.mugisha.repository;

import com.starter.mugisha.models.Purchased;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchasedRepository extends JpaRepository<Purchased, Long> {
}