package com.shopify;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import com.shopify.entities.Customer;
import com.shopify.exceptions.ResourceNotFoundException;
import com.shopify.repositories.CustomerRepository;
import com.shopify.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class CustomerTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegister() {
        Customer customer = new Customer();
        customer.setEmail("test@gmail.com");
        customer.setPassword("password");

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        Customer savedCustomer = customerService.register(customer);

        assertNotNull(savedCustomer);
        assertEquals("test@gmail.com", savedCustomer.getEmail());
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    public void testLogin_Success() {
        Customer customer = new Customer();
        customer.setEmail("test@gmail.com");
        customer.setPassword("password");

        when(customerRepository.findByEmail("test@gmail.com")).thenReturn(customer);
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        Optional<Customer> loggedInCustomer = customerService.login("test@gmail.com", "password");

        assertTrue(loggedInCustomer.isPresent());
        assertTrue(loggedInCustomer.get().isLoggedIn());
        verify(customerRepository, times(1)).findByEmail("test@gmail.com");
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    public void testLogin_Failure() {
        when(customerRepository.findByEmail("wrong@gmail.com")).thenReturn(null);

        Optional<Customer> loggedInCustomer = customerService.login("wrong@gmail.com", "wrongpassword");

        assertFalse(loggedInCustomer.isPresent());
        verify(customerRepository, times(1)).findByEmail("wrong@gmail.com");
        verify(customerRepository, times(0)).save(any(Customer.class));
    }

    @Test
    public void testLogout_Success() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setLoggedIn(true);

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        customerService.logout(1L);

        assertFalse(customer.isLoggedIn());
        verify(customerRepository, times(1)).findById(1L);
        verify(customerRepository, times(1)).save(customer);
    }

}

