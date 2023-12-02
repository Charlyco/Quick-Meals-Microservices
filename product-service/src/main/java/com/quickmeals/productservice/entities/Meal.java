package com.quickmeals.productservice.entities;

import com.quickmeals.productservice.customtypes.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer mealId;
    private String mealName;
    private Category category;
    private String imageUrl;
    private String description;
    private Double unitPrice;
    private Integer availableQuantity;
    private Boolean inStock;
    private Integer vendorId;

    public Meal(String mealName,
                Category category,
                String imageUrl,
                Double unitPrice,
                Integer availableQuantity,
                Integer vendorId) {
        this.mealName = mealName;
        this.category = category;
        this.imageUrl = imageUrl;
        this.unitPrice = unitPrice;
        this.availableQuantity = availableQuantity;
        this.vendorId = vendorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Meal meal)) return false;
        return Objects.equals(mealId, meal.mealId) &&
                Objects.equals(mealName, meal.mealName) &&
                Objects.equals(category, meal.category) &&
                Objects.equals(imageUrl, meal.imageUrl) &&
                Objects.equals(unitPrice, meal.unitPrice) &&
                Objects.equals(availableQuantity, meal.availableQuantity) &&
                Objects.equals(vendorId, meal.vendorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mealId, mealName, category, imageUrl, unitPrice, availableQuantity, vendorId);
    }

    @Override
    public String toString() {
        return "Meal{" +
                "mealId=" + mealId +
                ", mealName='" + mealName + '\'' +
                ", category='" + category + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", unitPrice=" + unitPrice +
                ", AvailableQuantity=" + availableQuantity +
                ", vendorId=" + vendorId +
                '}';
    }
}
