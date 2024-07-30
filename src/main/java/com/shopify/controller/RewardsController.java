package com.shopify.controller;

import com.shopify.service.RewardPointsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Month;
import java.util.Map;

@RestController
@RequestMapping("/rewards")
@Tag(name = "Reward Points", description = "APIs for managing reward points")
public class RewardsController {

    @Autowired
    private RewardPointsService rewardPointsService;


    @GetMapping("/customer/{id}")
    public ResponseEntity<?> getTotalRewardPointsByCustomerId(@PathVariable("id") Long customerId){
        return ResponseEntity.ok(rewardPointsService.getTotalRewardsOfCustomer(customerId));
    }

    @GetMapping("/monthly")
    public ResponseEntity<Map<Long, Map<Month, Integer>>> getMonthlyRewardPoints() {
        return ResponseEntity.ok(rewardPointsService.getRewardPointsPerMonth());
    }

    @GetMapping("/total")
    public ResponseEntity<Map<Long, Integer>> getTotalRewardPoints() {
        return ResponseEntity.ok(rewardPointsService.getTotalRewardPoints());
    }


}
