package com.quickmeals.authservice.entities;

import com.quickmeals.authservice.customtypes.BankDetail;
import com.quickmeals.authservice.customtypes.Location;
import com.quickmeals.authservice.customtypes.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vendors")
@DiscriminatorValue("V")
@PrimaryKeyJoinColumn(name = "vendorId")
public class Vendor extends User{
    private String businessName;
    private String businessDescription;
    private Double rating;
    private Integer totalSales;
    private Double walletBalance;
    private BankDetail bankDetail;
    @OneToMany(mappedBy = "vendor")
    private List<Meal> meals;
    @OneToMany(mappedBy = "selectedVendor")
    private List<MealOrder> orderList;

    public Vendor(String userName,
                  String password,
                  String fullName,
                  String email,
                  String phoneNumber,
                  String address,
                  Role role,
                  Location currentLocation,
                  String businessName,
                  String businessDescription,
                  Double rating,
                  List<Meal> meals,
                  List<MealOrder> orderList) {
        super(userName, password, fullName, email, phoneNumber, address, role, currentLocation);
        this.businessName = businessName;
        this.businessDescription = businessDescription;
        this.rating = rating;
        this.meals = meals;
        this.orderList = orderList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vendor vendor)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(businessName, vendor.businessName) && Objects.equals(businessDescription, vendor.businessDescription) && Objects.equals(rating, vendor.rating) && Objects.equals(meals, vendor.meals) && Objects.equals(orderList, vendor.orderList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), businessName, businessDescription, rating, meals, orderList);
    }

    @Override
    public String toString() {
        return "Vendor{" +
                "businessName='" + businessName + '\'' +
                ", businessDescription='" + businessDescription + '\'' +
                ", rating=" + rating +
                ", meals=" + meals +
                ", orderList=" + orderList +
                "} " + super.toString();
    }
}
