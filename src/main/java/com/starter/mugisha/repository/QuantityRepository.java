package com.starter.mugisha.repository;

import com.starter.mugisha.models.Quantity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuantityRepository extends JpaRepository<Quantity, Long> {
}