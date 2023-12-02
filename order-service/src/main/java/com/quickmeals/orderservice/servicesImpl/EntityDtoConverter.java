package com.quickmeals.orderservice.servicesImpl;

import com.quickmeals.orderservice.customtypes.*;
import com.quickmeals.orderservice.dtos.*;
import com.quickmeals.orderservice.entities.*;
import org.springframework.stereotype.Service;

@Service
public class EntityDtoConverter implements com.quickmeals.orderservice.services.EntityDtoConverter {

    @Override
    public MealOrderDto convertMealOderToDto(MealOrder mealOrder) {

        MealOrderDto mealOrderDto = new MealOrderDto();
        mealOrderDto.setOrderId(mealOrder.getOrderId());
        mealOrderDto.setRequestedMealIds(mealOrder.getRequestedMealIds());
        mealOrderDto.setTotalPrice(mealOrder.getTotalPrice());
        mealOrderDto.setOrderStatus(String.valueOf(mealOrder.getOrderStatus()));
        mealOrderDto.setOrderTime(String.valueOf(mealOrder.getOrderTime()));
        mealOrderDto.setCustomerId(mealOrder.getRequestingCustomerId());
        mealOrderDto.setSelectedVendorId(mealOrder.getSelectedVendorId());
        return mealOrderDto;
    }


    @Override
    public MealOrder convertDtoToMealOrder(MealOrderDto mealOrderDto) {

        MealOrder mealOrder = new MealOrder();
        mealOrder.setOrderId(mealOrderDto.getOrderId());
        mealOrder.setRequestedMealIds(mealOrderDto.getRequestedMealIds());
        mealOrder.setTotalPrice(mealOrderDto.getTotalPrice());
        mealOrder.setOrderStatus(MealOrderStatus.valueOf(mealOrderDto.getOrderStatus()));
        mealOrder.setRequestingCustomerId(mealOrderDto.getCustomerId());
        mealOrder.setSelectedVendorId(mealOrderDto.getSelectedVendorId());
        return mealOrder;
    }
}
