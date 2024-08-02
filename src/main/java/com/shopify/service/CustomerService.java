package com.shopify.service;

import com.shopify.entities.Customer;
import com.shopify.exceptions.ResourceNotFoundException;
import com.shopify.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer register(Customer customer) {
        return customerRepository.save(customer);
    }

    public Optional<Customer> login(String email, String password) {
        Customer customer = customerRepository.findByEmail(email);
        if (customer != null && customer.getPassword().equals(password)) {
            customer.setLoggedIn(true);
            customerRepository.save(customer);
            return Optional.of(customer);
        }
        return Optional.empty();
    }

    public void logout(Long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isPresent()) {
            customer.get().setLoggedIn(false);
            customerRepository.save(customer.get());

        } else {

            throw new ResourceNotFoundException("Customer", "CustomerId", customerId);
        }


    }
}
