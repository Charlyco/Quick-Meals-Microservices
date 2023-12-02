package com.quickmeals.productservice.servicesImpl;

import com.quickmeals.productservice.customtypes.Category;
import com.quickmeals.productservice.dtos.*;
import com.quickmeals.productservice.entities.*;
import org.springframework.stereotype.Service;

@Service
public class EntityDtoConverter implements com.quickmeals.productservice.services.EntityDtoConverter {

    @Override
    public MealDto convertMealToDto(Meal meal) {
        MealDto mealDto = new MealDto();
        mealDto.setMealId(meal.getMealId());
        mealDto.setMealName(meal.getMealName());
        mealDto.setCategory(String.valueOf(meal.getCategory()));
        mealDto.setImageUrl(meal.getImageUrl());
        mealDto.setDescription(meal.getDescription());
        mealDto.setUnitPrice(meal.getUnitPrice());
        mealDto.setAvailableQuantity(meal.getAvailableQuantity());
        mealDto.setInStock(meal.getInStock());
        mealDto.setVendorId(meal.getVendorId());
        return mealDto;
    }

    @Override
    public Meal convertDtoToMeal(MealDto mealDto) {
        Meal meal = new Meal();

        if (mealDto.getMealId() != null) {
            meal.setMealId(mealDto.getMealId());
        }
        meal.setMealName(mealDto.getMealName());
        meal.setCategory(Category.valueOf(mealDto.getCategory()));
        meal.setImageUrl(mealDto.getImageUrl());
        meal.setDescription(mealDto.getDescription());
        meal.setUnitPrice(mealDto.getUnitPrice());
        meal.setAvailableQuantity(mealDto.getAvailableQuantity());
        meal.setInStock(mealDto.getInStock());
        meal.setVendorId(mealDto.getVendorId());
        return meal;
    }
}
