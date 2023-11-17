package com.quickmeals.orderservice.repository;

import com.quickmeals.orderservice.entities.MealOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MealOrderRepository extends JpaRepository<MealOrder, Integer> {
    Optional<List<MealOrder>> findOrderBySelectedVendorId(Integer userId);
    Optional<List<MealOrder>> findOrdersByRequestingCustomerId(Integer userId);
}
