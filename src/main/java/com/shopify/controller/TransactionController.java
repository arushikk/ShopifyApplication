package com.shopify.controller;


import com.shopify.entities.CustomerTransaction;
import com.shopify.service.CustomerTransactionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/transactions")
@Tag(name = "Transactions", description = "APIs for managing transactions")
public class TransactionController {

    @Autowired
    private CustomerTransactionService customerTransactionService;

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<CustomerTransaction>> getTransactions(@PathVariable Long customerId) {
        return ResponseEntity.ok(customerTransactionService.getTransactions(customerId) );
    }

    @PostMapping("/customer/{customerId}")
    public ResponseEntity<CustomerTransaction> addTransaction(@PathVariable Long customerId, @RequestBody CustomerTransaction transaction) {
        return ResponseEntity.ok(customerTransactionService.addTransaction(customerId, transaction));
    }

    @PutMapping("/")
    public ResponseEntity<CustomerTransaction> updateTransaction(@RequestBody CustomerTransaction transaction) {
        return ResponseEntity.ok(customerTransactionService.updateTransaction(transaction));
    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<String> deleteTransaction(@PathVariable Long transactionId) {
        customerTransactionService.deleteTransaction(transactionId);
        return ResponseEntity.ok(String.format("%d deleted successfully",transactionId));
    }
}

