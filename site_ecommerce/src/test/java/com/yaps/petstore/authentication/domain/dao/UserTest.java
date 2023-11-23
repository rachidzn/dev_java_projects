package com.yaps.petstore.authentication.domain.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.yaps.petstore.authentication.domain.model.User;
import com.yaps.petstore.exception.CheckException;

/**
 * This class tests the Customer class
 */
public final class UserTest {

    //==================================
    //=            Test cases          =
    //==================================

    /**
     * This test tries to create an object with valid values.
     */
	@Test
    public void testCreateValidCustomer() {

        // Creates a valid customer
        try {
        	final User customer = new User("bill000", "Bill", "Gates");
        	assertEquals("Bill", customer.getFirstname());
        	assertEquals("Gates", customer.getLastname());
        	customer.checkData();
        } catch (CheckException e) {
            fail("Custumer data is OK!");
        }
    }

    /**
     * This test tries to create an object with invalid values.
     */
	@Test
    public void testCreateCustomerWithInvalidValues() throws Exception {

        // Creates objects with empty values
        try {
            final User customer = new User("1234", "", "Gates");
        	customer.checkData();
            fail("Object with empty values should not be created");
        } catch (CheckException e) {
        	assertEquals("Invalid customer first name", e.getMessage());
        }
        try {
            final User customer = new User("1234", "Bill", "");
        	customer.checkData();
            fail("Object with empty values should not be created");
        } catch (CheckException e) {
        	assertEquals("Invalid customer last name", e.getMessage());
        }

        // Creates objects with null values
        try {
            final User customer = new User("1234", null, "Gates");
        	customer.checkData();
            fail("Object with null values should not be created");
        } catch (CheckException e) {
        	assertEquals("Invalid customer first name", e.getMessage());
        }
        try {
            final User customer = new User("1234", "Bill", null);
        	customer.checkData();
            fail("Object with null values should not be created");
        } catch (CheckException e) {
        	assertEquals("Invalid customer last name", e.getMessage());
        }
    }

}
