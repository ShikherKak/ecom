package com.ecom.productcatalogservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PrductCatalogServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void checkEception()
	{
		Assertions.assertThrows(ArithmeticException.class,()-> div());
	}

	int div(){
		return 1/0;
	}
}
