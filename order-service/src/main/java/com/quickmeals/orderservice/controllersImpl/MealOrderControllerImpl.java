package com.quickmeals.orderservice.controllersImpl;

import com.quickmeals.orderservice.controllers.MealOrderController;
import com.quickmeals.orderservice.customtypes.MealOrderStatus;
import com.quickmeals.orderservice.dtos.MealOrderDto;
import com.quickmeals.orderservice.repository.MealOrderRepository;
import com.quickmeals.orderservice.services.MealOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;
@Controller
@RequiredArgsConstructor
public class MealOrderControllerImpl implements MealOrderController {
    private final MealOrderService mealOrderService;
    @Override
    public ResponseEntity<Integer> createNewOrder(MealOrderDto order) {
        return ResponseEntity.ok(mealOrderService.createNewMealOrder(order));
    }

    @Override
    public ResponseEntity<List<MealOrderDto>> getAllMealOrders() {
        return ResponseEntity.ok(mealOrderService.getAllMealOrders());
    }

    @Override
    public ResponseEntity<List<MealOrderDto>> findAllOrdersForCustomer(Integer userId) {
        return ResponseEntity.ok(mealOrderService.filterOrdersByCustomer(userId));
    }

    @Override
    public ResponseEntity<List<MealOrderDto>> findAllOrdersForVendor(Integer userId) {
        return ResponseEntity.ok(mealOrderService.filterOrdersByVendor(userId));
    }

    @Override
    public ResponseEntity<MealOrderDto> getOrderDetailsByIdAndUser(Integer orderId, Integer userId) {
        return ResponseEntity.ok(mealOrderService.getOrderDetailsByIdAndCustomer(orderId, userId));
    }

    @Override
    public ResponseEntity<MealOrderStatus> queryMealOrderStatus(Integer mealOrderId) {
        return ResponseEntity.ok(mealOrderService.queryMealOrderStatus(mealOrderId));
    }

    @Override
    public ResponseEntity<String> cancelOrder(Integer orderId) {
        if (mealOrderService.cancelOrder(orderId)) {
            ResponseEntity.ok("Order cancelled successfully");
        }
        return ResponseEntity.ok("Order not found");
    }

    @Override
    public ResponseEntity<MealOrderStatus> updateOrderStatus(Integer orderId, MealOrderStatus orderStatus) {
        return ResponseEntity.ok(mealOrderService.updateOrderStatus(orderId, orderStatus));
    }
}
