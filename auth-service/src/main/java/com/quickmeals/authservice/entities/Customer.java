package com.quickmeals.authservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customers")
@Entity()
@PrimaryKeyJoinColumn(name = "customerId")
@DiscriminatorValue("C")
public class Customer extends User{

    @OneToMany(mappedBy = "customer")
    private List<MealOrder> previousOrdersList;
    private List<Integer> favouriteFoodIdList;
    private List<Integer> favouriteVendorIdList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer customer)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(previousOrdersList, customer.previousOrdersList) && Objects.equals(favouriteFoodIdList, customer.favouriteFoodIdList) && Objects.equals(favouriteVendorIdList, customer.favouriteVendorIdList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), previousOrdersList, favouriteFoodIdList, favouriteVendorIdList);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "mealOrderList=" + previousOrdersList +
                ", favouriteFoodIdList=" + favouriteFoodIdList +
                ", favouriteVendorIdList=" + favouriteVendorIdList +
                "} " + super.toString();
    }
}
