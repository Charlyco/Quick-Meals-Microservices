package com.quickmeals.authservice.repository;

import com.quickmeals.authservice.entities.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Integer> {
    @Query("SELECT v FROM Vendor v WHERE v.businessName LIKE %:businessName%")
    List<Vendor> searchForVendorByBusinessName(@Param("businessName") String businessName);
}
