package com.shopify.service;

import com.shopify.entities.Customer;
import com.shopify.entities.CustomerTransaction;
import com.shopify.exceptions.ResourceNotFoundException;
import com.shopify.repositories.CustomerRepository;
import com.shopify.repositories.CustomerTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerTransactionService {

    @Autowired
    private CustomerTransactionRepository customerTransactionRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public List<CustomerTransaction> getTransactions(Long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        return customer.map(customerTransactionRepository::findByCustomer).orElseThrow(()-> new ResourceNotFoundException("" +
                "Customer" , "CustomerId" , customerId));
    }

    public CustomerTransaction addTransaction(Long customerId, CustomerTransaction transaction) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isPresent()) {
            transaction.setCustomer(customer.get());
            return customerTransactionRepository.save(transaction);
        }
        else
            throw new  ResourceNotFoundException("Customer" , "CustomerId" , customerId);
    }

    public void deleteTransaction(Long transactionId) {
        customerTransactionRepository.deleteById(transactionId);
    }

    public CustomerTransaction updateTransaction(CustomerTransaction transaction) {
        return customerTransactionRepository.save(transaction);
    }
}

