package com.shopify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc

@Import(dummyDataLoad.class)
public class RewardPointsControllerTest {

	@Autowired
	private MockMvc mockMvc;



	@Test
	public void testGetTotalRewardPointsByCustomerId() throws Exception {
		mockMvc.perform(get("/rewards/customer/2"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").value(1350));
	}

	@Test
	public void testGetMonthlyRewardPoints() throws Exception {
		mockMvc.perform(get("/rewards/monthly"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.size()").value(4))
				.andExpect(jsonPath("$.['1'].MARCH").value(250))
				.andExpect(jsonPath("$.['3'].JANUARY").value(490));
	}

	@Test
	public void testGetTotalRewardPoints() throws Exception {
		mockMvc.perform(get("/rewards/total"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.size()").value(4))
				.andExpect(jsonPath("$.['1']").value(365))
				.andExpect(jsonPath("$.['2']").value(1350));
	}
}
