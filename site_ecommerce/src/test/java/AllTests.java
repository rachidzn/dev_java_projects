

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.yaps.petstore.authentication.domain.dao.UserDAOTest;
import com.yaps.petstore.authentication.domain.dao.UserTest;
import com.yaps.petstore.authentication.domain.service.FindUsersControllerTest;
import com.yaps.petstore.authentication.domain.service.UserServiceTest;
import com.yaps.petstore.domain.dao.CategoryDAOTest;
import com.yaps.petstore.domain.dao.ItemDAOTest;
import com.yaps.petstore.domain.dao.OrderDAOTest;
import com.yaps.petstore.domain.dao.OrderLineDAOTest;
import com.yaps.petstore.domain.dao.ProductDAOTest;
import com.yaps.petstore.domain.service.CatalogServiceTest;
import com.yaps.petstore.domain.service.CreditCardServiceTest;
import com.yaps.petstore.domain.service.OrderServiceTest;
import com.yaps.petstore.util.UniqueIdGeneratorTest;
import com.yaps.petstore.web.CreateCustomerTestMockMvc;
import com.yaps.petstore.web.VisualiseCatalogTestMockMvc;
import com.yaps.petstore.web.WebTestMockMvc;

import junit.framework.JUnit4TestAdapter;

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
	UniqueIdGeneratorTest.class,
	WebTestMockMvc.class,
	VisualiseCatalogTestMockMvc.class,
	// barkbank interactions!
	UserServiceTest.class,
	OrderServiceTest.class,
	CreateCustomerTestMockMvc.class,
	CreditCardServiceTest.class
})
public class AllTests {
	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
		// org.junit.runner.JUnitCore.main("AllTests");
	}

	public static junit.framework.Test suite() {
		JUnit4TestAdapter adapter = new JUnit4TestAdapter(AllTests.class);
		return adapter;
	}
}

