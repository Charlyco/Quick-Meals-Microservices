package com.quickmeals.productservice.controllers;

import com.quickmeals.productservice.dtos.MealDto;
import com.quickmeals.productservice.entities.Meal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/meal")
public interface MealController {
    @GetMapping("/all")
    ResponseEntity<List<MealDto>> getAllMeals();

    @GetMapping("/meal")
    ResponseEntity<List<MealDto>> getMealsByName(@RequestParam("mealName") String name);

    @GetMapping("/{mealId}")
    ResponseEntity<MealDto> getMealById(@PathVariable("mealId") Integer mealId);

    @GetMapping("/vendorId")
    ResponseEntity<List<MealDto>> getMealsByVendor(@RequestParam("vendorId") Integer vendorId);

    @PostMapping
    ResponseEntity<Void> addNewMeal(@RequestBody MealDto mealDto);

    @DeleteMapping("/deleteById")
    ResponseEntity<Void> deleteMealById(@RequestParam("mealId") Integer mealId);

    @PutMapping("/updateMeal")
    ResponseEntity<Void> updateMeal(@RequestParam("mealId") Integer mealId, MealDto mealDto);

    @PutMapping("/stock")
    ResponseEntity<Boolean> updateMealInventory(@RequestParam("mealId") Integer mealId, Boolean inStock);

    @PutMapping("/inventory")
    ResponseEntity<Integer> updateAvailableQuantity(@RequestParam("mealId") Integer mealId, Integer quantityUpdate);
}
