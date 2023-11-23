package com.yaps.petstore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.yaps.petstore.authentication.domain.dao.UserDAOTest;
import com.yaps.petstore.authentication.domain.dao.UserTest;
import com.yaps.petstore.authentication.domain.service.FindUsersControllerTest;
import com.yaps.petstore.domain.dao.CategoryDAOTest;
import com.yaps.petstore.domain.dao.ItemDAOTest;
import com.yaps.petstore.domain.dao.OrderDAOTest;
import com.yaps.petstore.domain.dao.OrderLineDAOTest;
import com.yaps.petstore.domain.dao.ProductDAOTest;
import com.yaps.petstore.domain.service.CatalogServiceTest;
import com.yaps.petstore.domain.service.ShoppingCartTest;
import com.yaps.petstore.util.UniqueIdGeneratorTest;
import com.yaps.petstore.web.CreateCustomerTestMockMvc;
import com.yaps.petstore.web.VisualiseCatalogTestMockMvc;
import com.yaps.petstore.web.WebTestMockMvc;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	UserDAOTest.class,
	UserTest.class,
	FindUsersControllerTest.class,
	CategoryDAOTest.class,
	ItemDAOTest.class,
	OrderDAOTest.class,
	OrderLineDAOTest.class,
	ProductDAOTest.class,
	CatalogServiceTest.class,
	ShoppingCartTest.class,
	UniqueIdGeneratorTest.class,
	WebTestMockMvc.class,
	VisualiseCatalogTestMockMvc.class
})
public class Tp11ApplicationTests {

	@Test
	public void contextLoads() {
	}

}
