package com.udacity.pricing;

import com.udacity.pricing.domain.price.PriceRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PricingServiceApplicationTests {

	@Autowired
	private PriceRepository priceRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void autoInitOfRepository(){
		Assert.assertTrue("Repository should have been initialized",priceRepository.count()>0);
	}

}
