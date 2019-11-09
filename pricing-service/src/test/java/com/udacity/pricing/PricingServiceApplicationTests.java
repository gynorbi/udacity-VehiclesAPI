package com.udacity.pricing;

import com.udacity.pricing.domain.price.Price;
import com.udacity.pricing.domain.price.PriceRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PricingServiceApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private PriceRepository priceRepository;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void contextLoads() {
	}

	@Test
	public void autoInitOfRepository(){
		Assert.assertTrue("Repository should have been initialized",priceRepository.count()>0);
	}

	@Test
	public void getPriceForVehicleId(){
		ResponseEntity<Price> responseEntity =
				restTemplate.getForEntity("http://localhost:"+port+"/prices/1", Price.class);
		Assert.assertTrue("There should be a price for car id 1",responseEntity.getStatusCode() == HttpStatus.OK);
	}

}
