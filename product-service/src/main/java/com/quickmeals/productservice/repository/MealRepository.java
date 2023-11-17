package com.quickmeals.productservice.repository;

import com.quickmeals.productservice.entities.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface MealRepository extends JpaRepository<Meal, Integer> {

    Optional<List<Meal>> findMealsByVendorId(Integer userId);

    @Query("SELECT m FROM Meal m WHERE m.mealName LIKE %:mealName%")
    Optional<List<Meal>> findMealsByMealName(@Param("mealName") String mealName);
}
