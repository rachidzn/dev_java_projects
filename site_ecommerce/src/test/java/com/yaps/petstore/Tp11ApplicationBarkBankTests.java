package com.yaps.petstore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.yaps.petstore.authentication.domain.service.UserServiceTest;
import com.yaps.petstore.domain.service.CreditCardServiceTest;
import com.yaps.petstore.domain.service.OrderServiceTest;
import com.yaps.petstore.web.CreateCustomerTestMockMvc;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	UserServiceTest.class,
	OrderServiceTest.class,
	CreateCustomerTestMockMvc.class,
	CreditCardServiceTest.class
})
public class Tp11ApplicationBarkBankTests {

	@Test
	public void contextLoads() {
	}

}
