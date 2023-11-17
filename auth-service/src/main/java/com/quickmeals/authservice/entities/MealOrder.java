package com.quickmeals.authservice.entities;

import com.quickmeals.api.custom_types.MealOrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "meal order")
@Entity
public class MealOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderId;
    @OneToMany
    private Set<Meal> requestedMeals = new HashSet<>();
    private Double totalPrice;
    private MealOrderStatus orderStatus;
    private LocalDateTime orderTime;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Vendor selectedVendor;

    public MealOrder(Set<Meal> requestedMeals,
                     Double totalPrice,
                     MealOrderStatus orderStatus,
                     LocalDateTime orderTime,
                     Customer customer,
                     Vendor selectedVendor) {
        this.requestedMeals = requestedMeals;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
        this.orderTime = orderTime;
        this.customer = customer;
        this.selectedVendor = selectedVendor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MealOrder mealOrder)) return false;
        return Objects.equals(orderId, mealOrder.orderId) && Objects.equals(requestedMeals, mealOrder.requestedMeals) && Objects.equals(totalPrice, mealOrder.totalPrice) && orderStatus == mealOrder.orderStatus && Objects.equals(customer, mealOrder.customer) && Objects.equals(selectedVendor, mealOrder.selectedVendor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, requestedMeals, totalPrice, orderStatus, customer, selectedVendor);
    }

    @Override
    public String toString() {
        return "MealOrder{" +
                "orderId=" + orderId +
                ", requestedMeals=" + requestedMeals +
                ", totalPrice=" + totalPrice +
                ", orderStatus=" + orderStatus +
                ", customer=" + customer +
                ", selectedVendor=" + selectedVendor +
                '}';
    }
}
