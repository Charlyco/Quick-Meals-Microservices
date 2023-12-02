package com.quickmeals.orderservice.services;

import com.quickmeals.orderservice.customtypes.MealOrderStatus;
import com.quickmeals.orderservice.dtos.MealOrderDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MealOrderService {
    Integer createNewMealOrder(MealOrderDto mealOrderDto);
    List<MealOrderDto> getAllMealOrders();
    List<MealOrderDto> filterOrdersByCustomer(Integer userId);
    List<MealOrderDto> filterOrdersByVendor(Integer userId);
    MealOrderStatus updateOrderStatus(Integer orderId, MealOrderStatus orderStatus);
    MealOrderStatus queryMealOrderStatus(Integer orderId);
    Boolean cancelOrder(Integer orderId);
    Boolean deleteOrder(Integer orderId);
    MealOrderDto getOrderDetailsByIdAndCustomer(Integer orderId, Integer customerId);

}
