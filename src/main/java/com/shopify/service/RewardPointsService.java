package com.shopify.service;

import com.shopify.entities.CustomerTransaction;
import com.shopify.exceptions.ResourceNotFoundException;
import com.shopify.repositories.CustomerTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RewardPointsService {

    @Value("${rewardPoints.above50}")
    public int x50 ;

    @Value("${rewardPoints.above100}")
    public int x100;

    @Autowired
    private CustomerTransactionRepository customerTransactionRepository;

    public int calculatePoints(double amount) {
        int points = 0;
        if (amount > 100) {
            points += (amount - 100) * x100;
            amount = 100;
        }
        if (amount > 50) {
            points += (amount - 50)* x50;
        }
        return points;
    }


    public int getTotalRewardsOfCustomer(Long customerId){
        List< CustomerTransaction> transactions =customerTransactionRepository.findAll();
        int total = 0;
        for(CustomerTransaction transaction :transactions){
            if(transaction.getCustomer().getId()==customerId)
                total+= calculatePoints(transaction.getAmount());
        }

        return total;
    }
    public  Map<String, Integer> getRewardPointsPerMonthAndTotalByCustID(Long customerId) {
        List<CustomerTransaction> transactions = customerTransactionRepository.findByCustomerId(customerId);
        if(transactions.size()==0) throw new ResourceNotFoundException("Customer" , "CustomerId" , customerId);
        Map<String, Integer> pointsPerMonth = new HashMap<>();
        int total =0 ;

        for (CustomerTransaction transaction : transactions) {

            LocalDate localDate = transaction.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            Month month = localDate.getMonth();
            int points = calculatePoints(transaction.getAmount());
            total+= points;
            pointsPerMonth.put(month.toString() , pointsPerMonth.getOrDefault(month.toString() , 0)+ points);
        }

        pointsPerMonth.put("Total reward points of three months" , total);

        return pointsPerMonth;
    }

     public Map<Long, Map<Month, Integer>> getRewardPointsPerMonth() {
        List<CustomerTransaction> transactions = customerTransactionRepository.findAll();
        Map<Long, Map<Month, Integer>> pointsPerMonth = new HashMap<>();

        for (CustomerTransaction transaction : transactions) {
            Long customerId = transaction.getCustomer().getId();
            LocalDate localDate = transaction.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            Month month = localDate.getMonth();
            int points = calculatePoints(transaction.getAmount());

            if (!pointsPerMonth.containsKey(customerId)) {
                pointsPerMonth.put(customerId, new HashMap<Month, Integer>());
            }

            Map<Month, Integer> monthlyPoints = pointsPerMonth.get(customerId);
            if (!monthlyPoints.containsKey(month)) {
                monthlyPoints.put(month, 0);
            }

            monthlyPoints.put(month, monthlyPoints.get(month) + points);
        }

        return pointsPerMonth;
    }


    public Map<Long, Integer> getTotalRewardPoints() {
        List<CustomerTransaction> transactions = customerTransactionRepository.findAll();
        Map<Long, Integer> totalPoints = new HashMap<>();

        for (CustomerTransaction transaction : transactions) {
            Long customerId = transaction.getCustomer().getId();
            int points = calculatePoints(transaction.getAmount());

            if (!totalPoints.containsKey(customerId)) {
                totalPoints.put(customerId, 0);
            }

            totalPoints.put(customerId, totalPoints.get(customerId) + points);
        }

        return totalPoints;
    }
}

