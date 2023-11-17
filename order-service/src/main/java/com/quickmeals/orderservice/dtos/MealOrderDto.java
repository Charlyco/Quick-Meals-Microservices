package com.quickmeals.orderservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class MealOrderDto {
    private Integer orderId;
    private Set<Integer> mealIdList = new HashSet<>();
    private Double totalPrice;
    private String orderStatus;
    private String orderTime;
    private Integer customerId;
    private Integer selectedVendorId;
}
