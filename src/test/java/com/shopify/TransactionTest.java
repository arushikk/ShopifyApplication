package com.shopify;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.List;
import java.util.Arrays;

import com.shopify.entities.Customer;
import com.shopify.entities.CustomerTransaction;
import com.shopify.exceptions.ResourceNotFoundException;
import com.shopify.repositories.CustomerRepository;
import com.shopify.repositories.CustomerTransactionRepository;
import com.shopify.service.CustomerTransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TransactionTest {

    @InjectMocks
    private CustomerTransactionService customerTransactionService;

    @Mock
    private CustomerTransactionRepository customerTransactionRepository;

    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetTransactions_Success() {
        Customer customer = new Customer();
        customer.setId(1L);

        CustomerTransaction transaction1 = new CustomerTransaction();
        CustomerTransaction transaction2 = new CustomerTransaction();
        List<CustomerTransaction> transactions = Arrays.asList(transaction1, transaction2);

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(customerTransactionRepository.findByCustomer(customer)).thenReturn(transactions);

        List<CustomerTransaction> result = customerTransactionService.getTransactions(1L);

        assertEquals(2, result.size());
        verify(customerRepository, times(1)).findById(1L);
        verify(customerTransactionRepository, times(1)).findByCustomer(customer);
    }

    @Test
    public void testGetTransactions_CustomerNotFound() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            customerTransactionService.getTransactions(1L);
        });

        verify(customerRepository, times(1)).findById(1L);
        verify(customerTransactionRepository, times(0)).findByCustomer(any(Customer.class));
    }

    @Test
    public void testAddTransaction_Success() {
        Customer customer = new Customer();
        customer.setId(1L);

        CustomerTransaction transaction = new CustomerTransaction();
        transaction.setAmount(100.0);

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(customerTransactionRepository.save(transaction)).thenReturn(transaction);

        CustomerTransaction result = customerTransactionService.addTransaction(1L, transaction);

        assertNotNull(result);
        assertEquals(customer, result.getCustomer());
        verify(customerRepository, times(1)).findById(1L);
        verify(customerTransactionRepository, times(1)).save(transaction);
    }

    @Test
    public void testAddTransaction_CustomerNotFound() {
        CustomerTransaction transaction = new CustomerTransaction();

        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            customerTransactionService.addTransaction(1L, transaction);
        });

        verify(customerRepository, times(1)).findById(1L);
        verify(customerTransactionRepository, times(0)).save(any(CustomerTransaction.class));
    }

    @Test
    public void testDeleteTransaction() {
        customerTransactionService.deleteTransaction(1L);

        verify(customerTransactionRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testUpdateTransaction() {
        CustomerTransaction transaction = new CustomerTransaction();
        transaction.setId(1L);
        transaction.setAmount(150.0);

        when(customerTransactionRepository.save(transaction)).thenReturn(transaction);

        CustomerTransaction result = customerTransactionService.updateTransaction(transaction);

        assertNotNull(result);
        assertEquals(150.0, result.getAmount());
        verify(customerTransactionRepository, times(1)).save(transaction);
    }
}

