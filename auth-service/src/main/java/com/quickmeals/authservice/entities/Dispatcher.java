package com.quickmeals.authservice.entities;

import com.quickmeals.authservice.customtypes.BankDetail;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dispatcher")
@DiscriminatorValue("D")
@PrimaryKeyJoinColumn(name = "dispatcherId")
public class Dispatcher extends User{
    private Double rating;
    private Double walletBalance;
    private BankDetail bankDetail;
    private String dispatcherTag;
    private Integer dailyDeliveries;
    private Double bonuses;
}
