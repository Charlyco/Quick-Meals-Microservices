package com.quickmeals.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MealDto {
    private Integer mealId;
    private String mealName;
    private String category;
    private String imageUrl;
    private String description;
    private Double unitPrice;
    private Integer availableQuantity;
    private Boolean inStock;
    private Integer vendorId;
}
