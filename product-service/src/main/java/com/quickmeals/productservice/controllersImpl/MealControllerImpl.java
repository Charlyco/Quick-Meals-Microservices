package com.quickmeals.productservice.controllersImpl;

import com.quickmeals.productservice.controllers.MealController;
import com.quickmeals.productservice.dtos.MealDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class MealControllerImpl implements MealController {
    @Override
    public ResponseEntity<List<MealDto>> getAllMeals() {
        return null;
    }

    @Override
    public ResponseEntity<List<MealDto>> getMealsByName(String name) {
        return null;
    }

    @Override
    public ResponseEntity<MealDto> getMealById(Integer mealId) {
        return null;
    }

    @Override
    public ResponseEntity<List<MealDto>> getMealsByVendor(Integer vendorId) {
        return null;
    }

    @Override
    public ResponseEntity<Void> addNewMeal(MealDto mealDto) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteMealById(Integer mealId) {
        return null;
    }

    @Override
    public ResponseEntity<Void> updateMeal(Integer mealId, MealDto mealDto) {
        return null;
    }

    @Override
    public ResponseEntity<Boolean> updateMealInventory(Integer mealId, Boolean inStock) {
        return null;
    }

    @Override
    public ResponseEntity<Integer> updateAvailableQuantity(Integer mealId, Integer quantityUpdate) {
        return null;
    }
}
