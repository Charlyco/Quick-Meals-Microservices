package com.quickmeals.authservice.entities;

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
    private String category;
    private String imageUrl;
    private String description;
    private Double unitPrice;
    private Integer availableQuantity;
    private Boolean inStock;
    @ManyToOne
    private Vendor vendor;

    public Meal(String mealName,
                String category,
                String imageUrl,
                Double unitPrice,
                Integer availableQuantity,
                Vendor vendor) {
        this.mealName = mealName;
        this.category = category;
        this.imageUrl = imageUrl;
        this.unitPrice = unitPrice;
        this.availableQuantity = availableQuantity;
        this.vendor = vendor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Meal meal)) return false;
        return Objects.equals(mealId, meal.mealId) && Objects.equals(mealName, meal.mealName) && Objects.equals(category, meal.category) && Objects.equals(imageUrl, meal.imageUrl) && Objects.equals(unitPrice, meal.unitPrice) && Objects.equals(availableQuantity, meal.availableQuantity) && Objects.equals(vendor, meal.vendor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mealId, mealName, category, imageUrl, unitPrice, availableQuantity, vendor);
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
                ", vendor=" + vendor +
                '}';
    }
}
