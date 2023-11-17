package com.quickmeals.productservice.services;

import com.quickmeals.productservice.dtos.MealDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MealService {
    Boolean addMeal(MealDto mealDto);
    Boolean deleteMeal(Integer mealId);
    Boolean updateMeal(Integer mealId, MealDto mealDto);
    List<MealDto> loadAllMeals();
    List<MealDto> getMealsByVendor(Integer vendorId);
    List<MealDto> getMealsByName(String mealName);
    MealDto getMealById(Integer mealId);
    Boolean updateInventory(Integer mealId, Boolean inStock);
    Integer updateAvailableQuantity(Integer mealId, Integer quantityToUpdate);


}
