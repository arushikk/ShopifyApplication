package com.shopify.repositories;

import com.shopify.entities.Customer;
import com.shopify.entities.CustomerTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerTransactionRepository extends JpaRepository<CustomerTransaction, Long> {
    List<CustomerTransaction> findByCustomer(Customer customer);
}
