package com.shopify.repositories;

import com.shopify.entities.Customer;
import com.shopify.entities.RewardPoints;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RewardPointsRepository extends JpaRepository<RewardPoints, Long> {
    List<RewardPoints> findByCustomer(Customer customer);
}
