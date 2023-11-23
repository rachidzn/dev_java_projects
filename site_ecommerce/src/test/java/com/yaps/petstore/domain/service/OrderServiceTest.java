package com.yaps.petstore.domain.service;

import com.yaps.petstore.authentication.domain.dto.UserDTO;
import com.yaps.petstore.authentication.domain.service.UserService;
import com.yaps.petstore.domain.dto.*;
import com.yaps.petstore.exception.*;

import com.yaps.petstore.domain.service.OrderService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * This class tests the CatalogService class
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment =WebEnvironment.DEFINED_PORT)
public final class OrderServiceTest {
	
	@SuppressWarnings("unused")
	private static Logger logger = LogManager.getLogger(OrderServiceTest.class);
	
	private static final String COUNTER_NAME = "Order";
	
	@Autowired
	private OrderService orderService;
	@Autowired
	private CatalogService catalogService;
	@Autowired
	private UserService userService;
	@Autowired
    private CounterService counterService;
	
	private Random random = new Random();
	private int quantity =random.nextInt(30);
	
    //==================================
    //=            Test cases          =
    //==================================
    /**
     * This test tries to find an object with a invalid identifier.
     */
	@Test
    public void testServiceFindOrderWithInvalidValues() throws Exception {

        // Finds an object with a unknown identifier
        final Long id = Long.parseLong(counterService.getUniqueId(COUNTER_NAME));
        try {
        	orderService.findOrder(id);
            fail("Object with unknonw id should not be found");
        } catch (FinderException e) {
        }

        // Finds an object with an empty identifier
        try {
        	orderService.findOrder(new Long(random.nextLong()));
            fail("Object with empty id should not be found");
        } catch (FinderException e) {
        }

        // Finds an object with a null identifier
        try {
        	orderService.findOrder(0);
            fail("Object with null id should not be found");
        } catch (CheckException e) {
        }
    }

    /**
     * This method ensures that creating an object works. It first finds the object,
     * makes sure it doesn't exist, creates it and checks it then exists.
     */
	@Test
    public void testServiceCreateOrder() throws Exception {
		final Long id = Long.parseLong(counterService.getUniqueId(COUNTER_NAME));
        OrderDTO orderDTO = null;

        // Creates an object
        final long orderId = createOrder(id);

        // Ensures that the object exists
        try {
            orderDTO = findOrder(orderId);
        } catch (FinderException e) {
            fail("Object has been created it should be found");
        }

        // Checks that it's the right object
        checkOrder(orderDTO, id);

        // Cleans the test environment
        deleteOrder(orderId);

        try {
            findOrder(orderId);
            fail("Object has been deleted it shouldn't be found");
        } catch (FinderException e) {
        }
    }

    /**
     * This test tries to create an object with a invalid values.
     */
	@Test
    public void testServiceCreateOrderWithInvalidValues() throws Exception {
        OrderDTO orderDTO;

        // Creates an object with a null parameter
        try {
        	orderService.createOrder(null);
            fail("Object with null parameter should not be created");
        } catch (CreateException e) {
        }

        // Creates an object with empty values
        try {
            orderDTO = new OrderDTO(new String(), new String(), new String(), new String(), new String(), new String());
            orderService.createOrder(orderDTO);
            fail("Object with empty values should not be created");
        } catch (CheckException e) {
        }

        // Creates an object with null values
        try {
            orderDTO = new OrderDTO(null, null, null, null, null, null);
            orderService.createOrder(orderDTO);
            fail("Object with null values should not be created");
        } catch (CheckException e) {
        }
    }

    /**
     * This method ensures that creating an object with invalid credit card information
     * doesn't work.
     */
    // **************************************** NOT IMPLEMENTED YET ****************************************
		@Test
    public void testServiceCreateOrderWithInvalidCreditCard() throws Exception {
        final String id = counterService.getUniqueId(COUNTER_NAME);

        // Create Category
    	final String categoryId = counterService.getUniqueId("Category");
        final CategoryDTO categoryDTO = new CategoryDTO("cat" + categoryId, "name" + categoryId, "description" + categoryId);
        catalogService.createCategory(categoryDTO);
        // Create Product
        final String productId = counterService.getUniqueId("Product");
        final ProductDTO productDTO = new ProductDTO("prod" + productId, "name" + productId, "description" + productId);
        productDTO.setCategoryId("cat" + categoryId);
        catalogService.createProduct(productDTO);
        // Create Item
        final String itemId = counterService.getUniqueId("Item");
        final ItemDTO itemDTO = new ItemDTO("item" + itemId, "name" + itemId, Double.parseDouble(itemId));
        itemDTO.setProductId("prod" + productId);
        catalogService.createItem(itemDTO);

        // Create Customer
        String customerId = counterService.getUniqueId("User");
        final UserDTO customerDTO = new UserDTO("custo" + customerId, "firstname" + customerId, "lastname" + customerId);
        customerDTO.setPassword("pwd"+customerId);
        customerDTO.setRoleName("ROLE_USER");
        userService.createUser(customerDTO);

        // Creates one order line
        final OrderLineDTO oi1 = new OrderLineDTO(Integer.parseInt(id), itemDTO.getUnitCost());
        oi1.setItemId(itemDTO.getId());
//        final OrderLineDTO oi2 = new OrderLineDTO(Integer.parseInt(id), itemDTO.getUnitCost());
//        oi2.setItemId(itemDTO.getId());
        final Collection<OrderLineDTO> orderLines = new ArrayList<>();
        orderLines.add(oi1);
//        orderLines.add(oi2);

        // Create Order..
        OrderDTO orderDTO = new OrderDTO("firstname" + id, "lastname" + id, "street1" + id, "city" + id, "zip" + id, "country" + id);
        orderDTO.setCustomerId(customerDTO.getUsername());
        orderDTO.setOrderLines(orderLines);

        // ... with invalid credit card date
        orderDTO.setCreditCardExpiryDate("10/02");
        orderDTO.setCreditCardNumber("4564 1231 4564 2222");
        orderDTO.setCreditCardType("Visa");
        try {
            orderService.createOrder(orderDTO);
            fail("Credit card date was invalid. Object shouldn't be created");
        } catch (CheckException e) {
        }

        // ... with invalid credit card number for a visa
        orderDTO.setCreditCardExpiryDate("10/23");
        orderDTO.setCreditCardNumber("4564 1231 4564 1111");
        orderDTO.setCreditCardType("Visa");
        try {
            orderService.createOrder(orderDTO);
            fail("Credit card number was invalid. Object shouldn't be created");
        } catch (CheckException e) {
        }

        // The client doesn't pay with the credit card but with a cheque
        orderDTO.setCreditCardExpiryDate("");
        orderDTO.setCreditCardNumber("");
        orderDTO.setCreditCardType("");
        try {
            orderDTO = orderService.createOrder(orderDTO);
        } catch (CreateException e) {
            fail("Credit card wasn't used. Object should be created");
        }

        // Ensures that the object exists
        try {
            orderDTO = findOrder(orderDTO.getId());
        } catch (FinderException e) {
            fail("Object has been created it should be found");
        }

        // Cleans the test environment
        deleteOrder(orderDTO.getId());

        try {
            findOrder(orderDTO.getId());
            fail("Object has been deleted it shouldn't be found");
        } catch (FinderException e) {
        }
    }

    //==================================
    //=          Private Methods       =
    //==================================
    private OrderDTO findOrder(final long id) throws FinderException, CheckException {
        final OrderDTO orderDTO = orderService.findOrder(id);
        return orderDTO;
    }

//     Creates a category first, then a product linked to this category and an item linked to the product
//     Creates a Customer and an order linked to the customer
//     Creates an orderLine linked to the order and the item
    private long createOrder(final long id) throws CreateException, CheckException {
        // Create Category
    	final String categoryId = counterService.getUniqueId("Category");
        final CategoryDTO categoryDTO = new CategoryDTO("cat" + categoryId, "name" + categoryId, "description" + categoryId);
        catalogService.createCategory(categoryDTO);
        // Create Product
        final String productId = counterService.getUniqueId("Product");
        final ProductDTO productDTO = new ProductDTO("prod" + productId, "name" + productId, "description" + productId);
        productDTO.setCategoryId("cat" + categoryId);
        catalogService.createProduct(productDTO);
        // Create Item 1
        final String itemId = counterService.getUniqueId("Item");
        final ItemDTO itemDTO = new ItemDTO("item" + itemId, "name" + itemId, Double.parseDouble(itemId));
        itemDTO.setProductId("prod" + productId);
        catalogService.createItem(itemDTO);

        // Create Customer
        String customerId = counterService.getUniqueId(COUNTER_NAME);
        final UserDTO customerDTO = new UserDTO("custo" + customerId, "firstname" + id, "lastname" + id);
        customerDTO.setPassword("pwd"+customerId);
        customerDTO.setRoleName("ROLE_USER");
        userService.createUser(customerDTO);

        // Create Order
        final OrderDTO orderDTO = new OrderDTO("firstname" + id, "lastname" + id, "street1" + id, "city" + id, "zip" + id, "country" + id);
        orderDTO.setId(id);
        orderDTO.setStreet2("street2" + id);
        orderDTO.setCreditCardExpiryDate("10/23");
        orderDTO.setCreditCardNumber("4564 1231 4564 1222");
        orderDTO.setCreditCardType("Visa");
        orderDTO.setState("state" + id);
        orderDTO.setCustomerId(customerDTO.getUsername());

        // Creates 1 order lines
        final OrderLineDTO oi1 = new OrderLineDTO(quantity, itemDTO.getUnitCost());
        oi1.setItemId(itemDTO.getId());
        oi1.setOrderId(orderDTO.getId());
        final Collection<OrderLineDTO> orderLines = new ArrayList<>();
        orderLines.add(oi1);

        orderDTO.setOrderLines(orderLines);
        final OrderDTO result = orderService.createOrder(orderDTO);
        return result.getId();
    }

    private void deleteOrder(final long orderId) throws RemoveException, CheckException, FinderException {
    	final OrderDTO orderDTO = orderService.findOrder(orderId);
    	Collection<OrderLineDTO> orderlinesDTO = orderDTO.getOrderLines();
    	orderService.deleteOrder(orderId);
    	for(OrderLineDTO orderLine : orderlinesDTO) {
    		String itemId=orderLine.getItemId();
    		ItemDTO itemDTO = catalogService.findItem(itemId);
    		String productId=itemDTO.getProductId();
    		ProductDTO productDTO = catalogService.findProduct(productId);
    		String categoryId=productDTO.getCategoryId();
    		catalogService.deleteItem(itemId);
    		catalogService.deleteProduct(productId);
    		catalogService.deleteCategory(categoryId);
    	}
    	userService.deleteUser(orderDTO.getCustomerId());
    }

    private void checkOrder(final OrderDTO orderDTO, final Long id) {
        assertEquals("firstname", "firstname" + id, orderDTO.getFirstname());
        assertEquals("lastname", "lastname" + id, orderDTO.getLastname());
        assertEquals("city", "city" + id, orderDTO.getCity());
        assertEquals("country", "country" + id, orderDTO.getCountry());
        assertEquals("state", "state" + id, orderDTO.getState());
        assertEquals("street1", "street1" + id, orderDTO.getStreet1());
        assertEquals("street2", "street2" + id, orderDTO.getStreet2());
        assertEquals("zipcode", "zip" + id, orderDTO.getZipcode());
        assertEquals("CreditCardExpiryDate", "10/23", orderDTO.getCreditCardExpiryDate());
        assertEquals("CreditCardNumber", "4564 1231 4564 1222", orderDTO.getCreditCardNumber());
        assertEquals("CreditCardType", "Visa", orderDTO.getCreditCardType());
        assertEquals("order items", 1, orderDTO.getOrderLines().size());
        OrderLineDTO firstOrderLineDTO = ((OrderLineDTO)orderDTO.getOrderLines().iterator().next());
        assertEquals("First OrderLine quantity", quantity, firstOrderLineDTO.getQuantity());
    }

}
