package com.quickmeals.productservice.servicesImpl;

import com.quickmeals.productservice.dtos.MealDto;
import com.quickmeals.productservice.entities.Meal;
import com.quickmeals.productservice.repository.MealRepository;
import com.quickmeals.productservice.services.EntityDtoConverter;
import com.quickmeals.productservice.services.MealService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class MealServiceImpl implements MealService {
    private final MealRepository mealRepository;
    private final EntityDtoConverter entityDtoConverter;

    public MealServiceImpl(MealRepository mealRepository, EntityDtoConverter entityDtoConverter) {
        this.mealRepository = mealRepository;
        this.entityDtoConverter = entityDtoConverter;
    }

    @Override
    public Boolean addMeal(MealDto mealDto) {
        Meal meal = mealRepository.save(entityDtoConverter.convertDtoToMeal(mealDto));
        return meal.getMealId() != null;
    }

    @Override
    public Boolean deleteMeal(Integer mealId) {
        if (mealRepository.findById(mealId).isPresent()) {
            mealRepository.deleteById(mealId);
            return true;
        }else return false;
    }

    @Override
    public Boolean updateMeal(Integer mealId, MealDto mealDto) {
        if (mealRepository.findById(mealId).isPresent()) {
            Meal meal = entityDtoConverter.convertDtoToMeal(mealDto);
            mealRepository.save(meal);
            return true;
        }else return false;
    }

    @Override
    public List<MealDto> loadAllMeals() {
        List<MealDto> mealDtoList = new ArrayList<>();
        mealRepository.findAll().forEach(meal -> mealDtoList.add(entityDtoConverter.convertMealToDto(meal)));
        return mealDtoList;
    }

    @Override
    public List<MealDto> getMealsByVendor(Integer vendorId) {
        List<MealDto> mealDtoList = new ArrayList<>();
        mealRepository.findMealsByVendorId(vendorId).orElseThrow()
                .forEach(meal -> mealDtoList.add(entityDtoConverter.convertMealToDto(meal)));
        return mealDtoList;
    }

    @Override
    public List<MealDto> getMealsByName(String mealName) {
        List<MealDto> mealDtoList = new ArrayList<>();
        mealRepository.findMealsByMealName(mealName).orElseThrow()
                .forEach(meal -> mealDtoList.add(entityDtoConverter.convertMealToDto(meal)));
        return mealDtoList;
    }

    @Override
    public MealDto getMealById(Integer mealId) {
        Meal meal = mealRepository.findById(mealId).orElseThrow();
        return entityDtoConverter.convertMealToDto(meal);
    }

    @Override
    public Boolean updateInventory(Integer mealId, Boolean inStock) {
        if (mealRepository.findById(mealId).isPresent()) {
            Meal meal = mealRepository.findById(mealId).orElseThrow();
            meal.setInStock(inStock);
            return meal.getInStock();
        }
        else return false;
    }

    @Override
    public Integer updateAvailableQuantity(Integer mealId, Integer quantityToUpdate) {
        if (mealRepository.findById(mealId).isPresent()) {
            Meal meal = mealRepository.findById(mealId).orElseThrow();
            meal.setAvailableQuantity(quantityToUpdate);
            return meal.getAvailableQuantity();
        }
        return 0;
    }
}
