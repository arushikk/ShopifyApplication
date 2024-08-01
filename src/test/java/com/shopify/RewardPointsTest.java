package com.shopify;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.*;

import com.shopify.entities.Customer;
import com.shopify.entities.CustomerTransaction;
import com.shopify.repositories.CustomerTransactionRepository;
import com.shopify.service.RewardPointsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig
public class RewardPointsTest {

    @InjectMocks
    private RewardPointsService rewardPointsService;

    @Mock
    private CustomerTransactionRepository customerTransactionRepository;


    private int x50 ;

    private int x100 ;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        rewardPointsService.x50 = 1;
        rewardPointsService.x100 = 2;
    }

    @Test
    public void testCalculatePoints() {
        int points = rewardPointsService.calculatePoints(120);
        assertEquals(90, points);

        points = rewardPointsService.calculatePoints(70);
        assertEquals(20, points);

        points = rewardPointsService.calculatePoints(30);
        assertEquals(0, points);
    }

    @Test
    public void testGetTotalRewardsOfCustomer() {
        CustomerTransaction transaction1 = createTransaction(1L, 120, LocalDate.now());
        CustomerTransaction transaction2 = createTransaction(1L, 70, LocalDate.now());

        when(customerTransactionRepository.findAll()).thenReturn(Arrays.asList(transaction1, transaction2));

        int totalPoints = rewardPointsService.getTotalRewardsOfCustomer(1L);
        assertEquals(110, totalPoints); // 90 + 20 = 110
    }

    @Test
    public void testGetRewardPointsPerMonthAndTotalByCustID() {
        CustomerTransaction transaction1 = createTransaction(1L, 120, LocalDate.of(2023, Month.JANUARY, 1));
        CustomerTransaction transaction2 = createTransaction(1L, 70, LocalDate.of(2023, Month.FEBRUARY, 1));

        when(customerTransactionRepository.findByCustomerId(1L)).thenReturn(Arrays.asList(transaction1, transaction2));

        Map<String, Integer> pointsPerMonth = rewardPointsService.getRewardPointsPerMonthAndTotalByCustID(1L);

        assertEquals(3, pointsPerMonth.size());
        assertEquals(90, pointsPerMonth.get("JANUARY"));
        assertEquals(20, pointsPerMonth.get("FEBRUARY"));
        assertEquals(110, pointsPerMonth.get("Total reward points of three months"));
    }

    @Test
    public void testGetRewardPointsPerMonth() {
        CustomerTransaction transaction1 = createTransaction(1L, 120, LocalDate.of(2023, Month.JANUARY, 1));
        CustomerTransaction transaction2 = createTransaction(1L, 70, LocalDate.of(2023, Month.FEBRUARY, 1));
        CustomerTransaction transaction3 = createTransaction(2L, 150, LocalDate.of(2023, Month.JANUARY, 1));

        when(customerTransactionRepository.findAll()).thenReturn(Arrays.asList(transaction1, transaction2, transaction3));

        Map<Long, Map<Month, Integer>> pointsPerMonth = rewardPointsService.getRewardPointsPerMonth();

        assertEquals(2, pointsPerMonth.size());
        assertEquals(90, pointsPerMonth.get(1L).get(Month.JANUARY));
        assertEquals(20, pointsPerMonth.get(1L).get(Month.FEBRUARY));
        assertEquals(150, pointsPerMonth.get(2L).get(Month.JANUARY));
    }

    @Test
    public void testGetTotalRewardPoints() {
        CustomerTransaction transaction1 = createTransaction(1L, 120, LocalDate.now());
        CustomerTransaction transaction2 = createTransaction(2L, 150, LocalDate.now());

        when(customerTransactionRepository.findAll()).thenReturn(Arrays.asList(transaction1, transaction2));

        Map<Long, Integer> totalPoints = rewardPointsService.getTotalRewardPoints();

        assertEquals(2, totalPoints.size());
        assertEquals(90, (int) totalPoints.get(1L));
        assertEquals(150, (int) totalPoints.get(2L));
    }

    private CustomerTransaction createTransaction(Long customerId, double amount, LocalDate date) {
        Customer customer = new Customer();
        customer.setId(customerId);

        CustomerTransaction transaction = new CustomerTransaction();
        transaction.setCustomer(customer);
        transaction.setAmount(amount);
        transaction.setDate(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));

        return transaction;
    }
}

