package com.yaps.petstore.authentication.domain.service;

import com.yaps.petstore.authentication.domain.dto.UserDTO;
import com.yaps.petstore.authentication.domain.service.UserService;
import com.yaps.petstore.domain.service.CounterService;
import com.yaps.petstore.exception.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.rmi.RemoteException;
import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * This class tests the UserService class
 */
@RunWith(SpringRunner.class)
// *****  WebEnvironment.DEFINED_PORT *****
// ***** PERMET DE GARANTIR L'UTILISATION DU PORT DEFINI DANS LES PARAMETRES POUR LA CONNEXION A BARKBANK ***** 
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public final class UserServiceTest {
	
	private static final String COUNTER_NAME = "Customer";
	
//	@SuppressWarnings("unused")
	private static Logger logger = LogManager.getLogger(UserServiceTest.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
    private CounterService counterService;
	
    //==================================
    //=            Test cases          =
    //==================================
    /**
     * This test tries to find an object with a invalid identifier.
     */
	@Test
    public void testServiceFindCustomerWithInvalidValues() throws Exception {

        // Finds an object with a unknown identifier
        final String id = counterService.getUniqueId(COUNTER_NAME);
        try {
            userService.findUser(id);
            fail("Object with unknonw id should not be found");
        } catch (FinderException e) {
        }

        // Finds an object with an empty identifier
        try {
            userService.findUser(new String());
            fail("Object with empty id should not be found");
        } catch (CheckException e) {
        }

        // Finds an object with a null identifier
        try {
            userService.findUser(null);
            fail("Object with null id should not be found");
        } catch (CheckException e) {
        }
    }

    /**
     * This test ensures that the method findAll works. It does a first findAll, creates
     * a new object and does a second findAll.
     */
	@Test
    public void testServiceFindAllCustomers() throws Exception {
        final String id = counterService.getUniqueId(COUNTER_NAME);

        // First findAll
        final int firstSize = findAllCustomers();
        logger.info("firstSize is ... "+firstSize);
        // Creates an object
        createCustomer(id);

        // Ensures that the object exists
        try {
            findCustomer(id);
        } catch (ObjectNotFoundException e) {
            fail("Object has been created it should be found");
        }

        // Second findAll
        final int secondSize = findAllCustomers();

        // Checks that the collection size has increase of one
        if (firstSize + 1 != secondSize) fail("The collection size should have increased by 1");

        // Cleans the test environment
        deleteCustomer(id);

        try {
            findCustomer(id);
            fail("Object has been deleted it shouldn't be found");
        } catch (FinderException e) {
        }
    }

    /**
     * This method ensures that creating an object works. It first finds the object,
     * makes sure it doesn't exist, creates it and checks it then exists.
     */
	@Test
    public void testServiceCreateCustomer() throws Exception {
        final String id = counterService.getUniqueId(COUNTER_NAME);
        UserDTO customerDTO = null;

        // Ensures that the object doesn't exist
        try {
            findCustomer(id);
            fail("Object has not been created yet it shouldn't be found");
        } catch (FinderException e) {
        }

        // Creates an object
        createCustomer(id);

        // Ensures that the object exists
        try {
            customerDTO = findCustomer(id);
        } catch (FinderException e) {
            fail("Object has been created it should be found");
        }

        // Checks that it's the right object
        checkCustomer(customerDTO, id);

        // Creates an object with the same identifier. An exception has to be thrown
        try {
            createCustomer(id);
            fail("An object with the same id has already been created");
        } catch (DuplicateKeyException e) {
        }

        // Cleans the test environment
        deleteCustomer(id);

        try {
            findCustomer(id);
            fail("Object has been deleted it shouldn't be found");
        } catch (FinderException e) {
        }
    }

    /**
     * This test tries to create an object with a invalid values.
     */
    @Test
    public void testServiceCreateCustomerWithInvalidValues() throws Exception {
        UserDTO customerDTO;

        // Creates an object with a null parameter
        try {
            userService.createUser(null);
            fail("Object with null parameter should not be created");
        } catch (CreateException e) {
        }
        
     // Creates an object with an invalid pwd
        try {
        	customerDTO = new UserDTO("username", "firstname", "lastname");
        	customerDTO.setRoleName("ROLE_USER");
            customerDTO.setPassword("abc");
            userService.createUser(customerDTO);
            fail("Object with short password should not be created");
        } catch (CreateException e) {
        }

        // Creates an object with empty values
        try {
            customerDTO = new UserDTO(new String(), new String(), new String());
            customerDTO.setPassword("validPwd");
            userService.createUser(customerDTO);
            fail("Object with empty values should not be created");
        } catch (CheckException e) {
        }

        // Creates an object with null values
        try {
            customerDTO = new UserDTO(null, null, null);
            customerDTO.setPassword("validPwd");
            userService.createUser(customerDTO);
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
    public void testServiceCreateCustomerWithInvalidCreditCard() throws Exception {
        final String id = counterService.getUniqueId(COUNTER_NAME);
        UserDTO customerDTO = new UserDTO("custo" + id, "firstname" + id, "lastname" + id);
        customerDTO.setPassword("pwd" + id);
        customerDTO.setRoleName("ROLE_USER");
        // Invalid credit card date
        customerDTO.setCreditCardExpiryDate("10/02");
        customerDTO.setCreditCardNumber("4564 1231 4564 2222");
        customerDTO.setCreditCardType("Visa");
        try {
            userService.createUser(customerDTO);
            fail("Credit card date was invalid. Object shouldn't be created");
        } catch (CheckException e) {
        }

        // Invalid credit card number for a visa
        customerDTO.setCreditCardExpiryDate("10/23");
        customerDTO.setCreditCardNumber("4564 1231 4564 1111");
        customerDTO.setCreditCardType("Visa");
        try {
            userService.createUser(customerDTO);
            fail("Credit card number was invalid. Object shouldn't be created");
        } catch (CheckException e) {
        }

        // The client doesn't pay with the credit card but with a cheque
        customerDTO.setCreditCardExpiryDate("");
        customerDTO.setCreditCardNumber("");
        customerDTO.setCreditCardType("");
        try {
            userService.createUser(customerDTO);
        } catch (CreateException e) {
            fail("Credit card wasn't used. Object should be created");
        }

        // Ensures that the object exists
        try {
            findCustomer(id);
        } catch (FinderException e) {
            fail("Object has been created it should be found");
        }

        // Cleans the test environment
        deleteCustomer(id);

        try {
            findCustomer(id);
            fail("Object has been deleted it shouldn't be found");
        } catch (FinderException e) {
        }
    }

    /**
     * This test make sure that updating an object success
     */
    @Test
    public void testServiceUpdateCustomer() throws Exception {
        final String id = counterService.getUniqueId(COUNTER_NAME);
        final String updatedId = counterService.getUniqueId(COUNTER_NAME);

        // Creates an object
        createCustomer(id);

        // Ensures that the object exists
        UserDTO customerDTO = null;
        try {
            customerDTO = findCustomer(id);
        } catch (ObjectNotFoundException e) {
            fail("Object has been created it should be found");
        }

        // Checks that it's the right object
        checkCustomer(customerDTO, id);

        // Updates the object with new values
        updateCustomer(customerDTO, updatedId);

        // Ensures that the object still exists
        UserDTO customerUpdated = null;
        try {
            customerUpdated = findCustomer(id);
        } catch (ObjectNotFoundException e) {
            fail("Object should be found");
        }

        // Checks that the object values have been updated
        checkCustomer(customerUpdated, updatedId);

        // Cleans the test environment
        deleteCustomer(id);

        try {
            findCustomer(id);
            fail("Object has been deleted it shouldn't be found");
        } catch (FinderException e) {
        }
    }

    /**
     * This test tries to update an object with a invalid values.
     */
    @Test
    public void testServiceUpdateCustomerWithInvalidValues() throws Exception {
        UserDTO customerDTO;

        // Updates an object with a null parameter
        try {
            userService.updateUser(null);
            fail("Object with null parameter should not be updated");
        } catch (UpdateException e) {
        }

        // Updates an object with empty values
        try {
            customerDTO = new UserDTO(new String(), new String(), new String());
            userService.updateUser(customerDTO);
            fail("Object with empty values should not be updated");
        } catch (CheckException e) {
        }

        // Updates an object with null values
        try {
            customerDTO = new UserDTO(null, null, null);
            userService.updateUser(customerDTO);
            fail("Object with null values should not be updated");
        } catch (CheckException e) {
        }
    }

    /**
     * This method ensures that updating an object with invalid credit card information
     * doesn't work.
     */

    @Test
    public void testServiceUpdateCustomerWithInvalidCreditCard() throws Exception {
        final String id = counterService.getUniqueId(COUNTER_NAME);

        // Creates an object
        createCustomer(id);

        // Ensures that the object exists
       UserDTO customerDTO = null;
        try {
            customerDTO = findCustomer(id);
        } catch (FinderException e) {
            fail("Object has been created it should be found");
        }

        // Checks that it's the right object
        checkCustomer(customerDTO, id);

        // Invalid credit card date
        customerDTO.setCreditCardExpiryDate("10/02");
        customerDTO.setCreditCardNumber("4564 1231 4564 2222");
        customerDTO.setCreditCardType("Visa");
        try {
            userService.updateUser(customerDTO);
            fail("Credit card date was invalid. Object shouldn't be updated");
        } catch (CheckException e) {
        }

        // Invalid credit card number for a visa
        customerDTO.setCreditCardExpiryDate("10/23");
        customerDTO.setCreditCardNumber("4564 1231 4564 1111");
        customerDTO.setCreditCardType("Visa");
        try {
            userService.updateUser(customerDTO);
            fail("Credit card number was invalid. Object shouldn't be updated");
        } catch (CheckException e) {
        }

        // The client doesn't pay with the credit card but with a cheque
        customerDTO.setCreditCardExpiryDate("");
        customerDTO.setCreditCardNumber("");
        customerDTO.setCreditCardType("");
        try {
            userService.updateUser(customerDTO);
        } catch (UpdateException e) {
            fail("Credit card wasn't used. Object should be updated");
        }

        // Ensures that the object exists
        try {
            findCustomer(id);
        } catch (ObjectNotFoundException e) {
            fail("Object has been updated it should be found");
        }

        // Cleans the test environment
        deleteCustomer(id);

        try {
            findCustomer(id);
            fail("Object has been deleted it shouldn't be found");
        } catch (FinderException e) {
        }
    }

    /**
     * This test ensures that the system cannont remove an unknown object
     */
    @Test
    public void testServiceDeleteUnknownCustomer() throws Exception {
        final String id = counterService.getUniqueId(COUNTER_NAME);

        // Ensures that the object doesn't exist
        try {
            findCustomer(id);
            fail("Object has not been created it shouldn't be found");
        } catch (FinderException e) {
        }

        // Delete the unknown object
        try {
            deleteCustomer(id);
            fail("Deleting an unknown object should break");
        } catch (RemoveException e) {
        }
    }

    //==================================
    //=          Private Methods       =
    //==================================

    private UserDTO findCustomer(final String id) throws FinderException, CheckException {
        final UserDTO customerDTO = userService.findUser("custo" + id);
        return customerDTO;
    }

    private int findAllCustomers() throws FinderException, RemoteException {
        try {
            return ((Collection<UserDTO>) userService.findUsers()).size();
        } catch (ObjectNotFoundException e) {
        	logger.warn("exception is ... "+e.getMessage());
            return 0;
        }
    }

    private void createCustomer(final String id) throws CreateException, CheckException, RemoteException {
        final UserDTO customerDTO = new UserDTO("custo" + id, "firstname" + id, "lastname" + id);
        customerDTO.setCity("city" + id);
        customerDTO.setCountry("" + id);
        customerDTO.setState("" + id);
        customerDTO.setStreet1("street1" + id);
        customerDTO.setStreet2("street2" + id);
        customerDTO.setTelephone("phone" + id);
        customerDTO.setEmail("email" + id);
        customerDTO.setPassword("pwd" + id);
        customerDTO.setZipcode("zip" + id);
        customerDTO.setCreditCardExpiryDate("10/23");
        customerDTO.setCreditCardNumber("4564 1231 4564 1222");
        customerDTO.setCreditCardType("Visa");
        customerDTO.setRoleName("ROLE_USER");
        userService.createUser(customerDTO);
    }

    private void updateCustomer(final UserDTO customerDTO, final String id) throws UpdateException, CheckException, RemoteException {
        customerDTO.setFirstname("firstname" + id);
        customerDTO.setLastname("lastname" + id);
        customerDTO.setCity("city" + id);
        customerDTO.setCountry("" + id);
        customerDTO.setState("" + id);
        customerDTO.setStreet1("street1" + id);
        customerDTO.setStreet2("street2" + id);
        customerDTO.setTelephone("phone" + id);
        customerDTO.setEmail("email" + id);
        customerDTO.setZipcode("zip" + id);
        customerDTO.setCreditCardExpiryDate("10/23");
        customerDTO.setCreditCardNumber("4564 1231 4564 1222");
        customerDTO.setCreditCardType("Visa");
        userService.updateUser(customerDTO);
    }

    private void deleteCustomer(final String id) throws RemoveException, CheckException, RemoteException {
        userService.deleteUser("custo" + id);
    }

    private void checkCustomer(final UserDTO customerDTO, final String id) {
        assertEquals("firstname", "firstname" + id, customerDTO.getFirstname());
        assertEquals("lastname", "lastname" + id, customerDTO.getLastname());
        assertEquals("city", "city" + id, customerDTO.getCity());
        assertEquals("country", "" + id, customerDTO.getCountry());
        assertEquals("state", "" + id, customerDTO.getState());
        assertEquals("street1", "street1" + id, customerDTO.getStreet1());
        assertEquals("street2", "street2" + id, customerDTO.getStreet2());
        assertEquals("telephone", "phone" + id, customerDTO.getTelephone());
        assertEquals("email", "email" + id, customerDTO.getEmail());
        assertEquals("zipcode", "zip" + id, customerDTO.getZipcode());
        assertEquals("CreditCardExpiryDate", "10/23", customerDTO.getCreditCardExpiryDate());
        assertEquals("CreditCardNumber", "4564 1231 4564 1222", customerDTO.getCreditCardNumber());
        assertEquals("CreditCardType", "Visa", customerDTO.getCreditCardType());
    }
    
}