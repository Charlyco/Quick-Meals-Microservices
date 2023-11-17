package com.quickmeals.orderservice.services;

import com.quickmeals.orderservice.dtos.*;
import com.quickmeals.orderservice.entities.*;

public interface EntityDtoConverter {

    MealOrderDto convertMealOderToDto(MealOrder mealOrder);
    MealOrder convertDtoToMealOrder(MealOrderDto mealOrderDto);

}
