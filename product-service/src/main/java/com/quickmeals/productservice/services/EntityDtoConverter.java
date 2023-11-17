package com.quickmeals.productservice.services;

import com.quickmeals.productservice.dtos.*;
import com.quickmeals.productservice.entities.*;
import org.springframework.stereotype.Service;

@Service
public interface EntityDtoConverter {

    MealDto convertMealToDto(Meal meal);
    Meal convertDtoToMeal(MealDto mealsDto);


}
