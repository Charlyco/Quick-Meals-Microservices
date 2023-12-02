package com.quickmeals.orderservice.controllers;

import com.quickmeals.orderservice.dtos.MealOrderDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.quickmeals.orderservice.customtypes.MealOrderStatus;

import java.util.List;

@RestController
@RequestMapping("api/v1/order-service/mealOrder")
public interface MealOrderController {

    @PostMapping()
    ResponseEntity<Integer> createNewOrder(@RequestBody MealOrderDto order);

    @GetMapping
    ResponseEntity<List<MealOrderDto>> getAllMealOrders();

    @GetMapping("/customer")
    ResponseEntity<List<MealOrderDto>> findAllOrdersForCustomer(@RequestParam("customerId") Integer userId);

    @GetMapping("/vendor")
    ResponseEntity<List<MealOrderDto>> findAllOrdersForVendor(@RequestParam("vendorId") Integer userId);

    @GetMapping("/{orderId}")
    ResponseEntity<MealOrderDto> getOrderDetailsByIdAndUser(
            @PathVariable("orderId") Integer orderId,
            @RequestParam("userId") Integer userId);
    @GetMapping("/status")
    ResponseEntity<MealOrderStatus> queryMealOrderStatus(Integer mealOrderId);

    @PutMapping("/cancel/{orderId}")
    ResponseEntity<String> cancelOrder(@PathVariable("orderId") Integer orderId);

    @PutMapping("/status/{orderId}")
    ResponseEntity<MealOrderStatus> updateOrderStatus(
            @PathVariable("orderId") Integer orderId,
            @RequestParam("orderStatus") MealOrderStatus orderStatus
    );
}
