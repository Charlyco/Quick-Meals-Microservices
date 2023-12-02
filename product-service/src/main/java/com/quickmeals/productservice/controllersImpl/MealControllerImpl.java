package com.quickmeals.productservice.controllersImpl;

import com.quickmeals.productservice.controllers.MealController;
import com.quickmeals.productservice.dtos.MealDto;
import com.quickmeals.productservice.repository.MealRepository;
import com.quickmeals.productservice.services.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class MealControllerImpl implements MealController {
    private final MealService mealService;
    @Override
    public ResponseEntity<List<MealDto>> getAllMeals() {
        return ResponseEntity.ok(mealService.loadAllMeals());
    }

    @Override
    public ResponseEntity<List<MealDto>> getMealsByCategory(String category) {
        return ResponseEntity.ok(mealService.getMealsByCategory(category));
    }

    @Override
    public ResponseEntity<List<MealDto>> getMealsByName(String name) {
        return ResponseEntity.ok(mealService.getMealsByName(name));
    }

    @Override
    public ResponseEntity<MealDto> getMealById(Integer mealId) {
        return ResponseEntity.ok(mealService.getMealById(mealId));
    }

    @Override
    public ResponseEntity<List<MealDto>> getMealsByVendor(Integer vendorId) {
        return ResponseEntity.ok(mealService.getMealsByVendor(vendorId));
    }

    /*
    * Successfully adding a new meal to the database returns ResponseEntity.noContent
    * Else return ResponseEntity.badRequest
    * */
    @Override
    public ResponseEntity<Void> addNewMeal(MealDto mealDto) {
        Boolean isAdded = mealService.addMeal(mealDto);
        if (isAdded) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<Void> deleteMealById(Integer mealId) {
        Boolean isDeleted = mealService.deleteMeal(mealId);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }

    /*
    * @Param mealId is the Id of the meal to be updated
    * @Param mealDto is the updated meal object to be  used to replace the existing copy found with the @Param
    * mealId */
    @Override
    public ResponseEntity<Void> updateMeal(Integer mealId, MealDto mealDto) {
        Boolean isUpdated  = mealService.updateMeal(mealId, mealDto);
        if (isUpdated) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<Boolean> updateMealInventory(Integer mealId, Boolean inStock) {
        Boolean isInStock = mealService.updateInventory(mealId, inStock);
        return ResponseEntity.ok(isInStock);
    }

    @Override
    public ResponseEntity<Integer> updateAvailableQuantity(Integer mealId, Integer quantityUpdate) {
        return ResponseEntity.ok(mealService.updateAvailableQuantity(mealId, quantityUpdate));
    }
}
