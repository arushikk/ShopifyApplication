package com.shopify.controller;

import com.shopify.dto.CustomerResponse;
import com.shopify.dto.LoginRequest;
import com.shopify.entities.Customer;
import com.shopify.service.CustomerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/customers")
@Tag(name = "Customers", description = "APIs for managing customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<Customer> register(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.register(customer));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<Customer> customer = customerService.login(loginRequest.getEmail(), loginRequest.getPassword());

        if (customer.isPresent()) {
            return ResponseEntity.ok(new CustomerResponse(customer.get().getId(), customer.get().getName(), customer.get().getEmail(), customer.get().isLoggedIn(), customer.get().getTransactions()));
        } else {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestParam Long customerId) {
        customerService.logout(customerId);
        return ResponseEntity.ok().body("Customer with customerID " + customerId + " is logged out");
    }
}

