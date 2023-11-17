package com.quickmeals.orderservice.controllersImpl;

import com.quickmeals.orderservice.controllers.MealOrderController;
import com.quickmeals.orderservice.customtypes.MealOrderStatus;
import com.quickmeals.orderservice.dtos.MealOrderDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class MealOrderControllerImpl implements MealOrderController {
    @Override
    public ResponseEntity<Integer> createNewOrder(MealOrderDto order) {
        return null;
    }

    @Override
    public ResponseEntity<List<MealOrderDto>> getAllMealOrders() {
        return null;
    }

    @Override
    public ResponseEntity<List<MealOrderDto>> findAllOrdersForCustomer(Integer userId) {
        return null;
    }

    @Override
    public ResponseEntity<List<MealOrderDto>> findAllOrdersForVendor(Integer userId) {
        return null;
    }

    @Override
    public ResponseEntity<MealOrderDto> getOrderDetailsByIdAndUser(Integer orderId, Integer userId) {
        return null;
    }

    @Override
    public ResponseEntity<MealOrderStatus> queryMealOrderStatus(Integer mealOrderId) {
        return null;
    }

    @Override
    public ResponseEntity<String> cancelOrder(Integer orderId) {
        return null;
    }

    @Override
    public ResponseEntity<MealOrderStatus> updateOrderStatus(Integer orderId, MealOrderStatus orderStatus) {
        return null;
    }
}
