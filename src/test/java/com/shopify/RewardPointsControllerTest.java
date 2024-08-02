package com.shopify;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

import com.shopify.service.RewardPointsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Month;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
@Import(dummyDataLoad.class)
public class RewardPointsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RewardPointsService rewardPointsService;

    @Test
    public void testGetTotalRewardPointsByCustomerId() throws Exception {
        when(rewardPointsService.getTotalRewardsOfCustomer(2L)).thenReturn(1350);

        mockMvc.perform(get("/rewards/customer/2")).andExpect(status().isOk()).andExpect(jsonPath("$").value(1350));
    }

    @Test
    public void testGetMonthlyRewardPoints() throws Exception {
        Map<Long, Map<Month, Integer>> monthlyRewards = new HashMap<>();
        Map<Month, Integer> customer1 = new HashMap<>();
        customer1.put(Month.JULY, 250);
        monthlyRewards.put(1L, customer1);
        Map<Month, Integer> customer3 = new HashMap<>();
        customer3.put(Month.MAY, 490);
        monthlyRewards.put(3L, customer3);

        when(rewardPointsService.getRewardPointsPerMonth()).thenReturn(monthlyRewards);

        mockMvc.perform(get("/rewards/monthly")).andExpect(status().isOk()).andExpect(jsonPath("$.size()").value(2)).andExpect(jsonPath("$.['1'].JULY").value(250)).andExpect(jsonPath("$.['3'].MAY").value(490));
    }

    @Test
    public void testGetTotalRewardPoints() throws Exception {
        Map<Long, Integer> totalRewards = new HashMap<>();
        totalRewards.put(1L, 455);
        totalRewards.put(2L, 1350);

        when(rewardPointsService.getTotalRewardPoints()).thenReturn(totalRewards);

        mockMvc.perform(get("/rewards/total")).andExpect(status().isOk()).andExpect(jsonPath("$.size()").value(2)).andExpect(jsonPath("$.['1']").value(455)).andExpect(jsonPath("$.['2']").value(1350));
    }

    @Test
    public void testGetMonthlyAndTotalRewardPointsByCustomerId() throws Exception {
        Map<String, Integer> summary = new HashMap<>();
        summary.put("MAY", 150);
        summary.put("JUNE", 300);
        summary.put("Total reward points of three months", 450);

        when(rewardPointsService.getRewardPointsPerMonthAndTotalByCustID(1L)).thenReturn(summary);

        mockMvc.perform(get("/rewards/customer/summary/1")).andExpect(status().isOk()).andExpect(jsonPath("$.size()").value(3)).andExpect(jsonPath("$.['MAY']").value(150)).andExpect(jsonPath("$.['JUNE']").value(300)).andExpect(jsonPath("$.['Total reward points of three months']").value(450));
    }
}
