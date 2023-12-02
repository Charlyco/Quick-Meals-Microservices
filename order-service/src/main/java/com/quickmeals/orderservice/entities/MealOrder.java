package com.quickmeals.orderservice.entities;

import com.quickmeals.orderservice.customtypes.MealOrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MealOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderId;
    private LocalDateTime orderTime;
    private Integer requestingCustomerId;
    private Integer selectedVendorId;
    private Set<Integer> requestedMealIds;
    private Double totalPrice;
    private MealOrderStatus orderStatus;

    public MealOrder(Set<Integer> requestedMealIds,
                     Double totalPrice,
                     MealOrderStatus orderStatus,
                     LocalDateTime orderTime,
                     Integer requestingCustomerId,
                     Integer selectedVendorId) {
        this.requestedMealIds = requestedMealIds;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
        this.orderTime = orderTime;
        this.requestingCustomerId = requestingCustomerId;
        this.selectedVendorId = selectedVendorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MealOrder mealOrder)) return false;
        return Objects.equals(orderId, mealOrder.orderId) && Objects.equals(requestedMealIds, mealOrder.requestedMealIds) && Objects.equals(totalPrice, mealOrder.totalPrice) && orderStatus == mealOrder.orderStatus && Objects.equals(orderTime, mealOrder.orderTime) && Objects.equals(requestingCustomerId, mealOrder.requestingCustomerId) && Objects.equals(selectedVendorId, mealOrder.selectedVendorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, requestedMealIds, totalPrice, orderStatus, orderTime, requestingCustomerId, selectedVendorId);
    }

    @Override
    public String toString() {
        return "MealOrder{" +
                "orderId=" + orderId +
                ", requestedMealIds=" + requestedMealIds +
                ", totalPrice=" + totalPrice +
                ", orderStatus=" + orderStatus +
                ", orderTime=" + orderTime +
                ", requestingCustomerId=" + requestingCustomerId +
                ", selectedVendorId=" + selectedVendorId +
                '}';
    }
}
