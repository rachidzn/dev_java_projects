package com.yaps.petstore.domain.service;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.barkbank.CreditCardStatus;
import com.yaps.petstore.domain.model.CreditCard;
import com.yaps.petstore.exception.CheckException;

/**
 * This class tests the CreditCardService class
 */
@RunWith(SpringRunner.class)
// *****  WebEnvironment.DEFINED_PORT *****
// ***** PERMET DE GARANTIR L'UTILISATION DU PORT DEFINI DANS LES PARAMETRES POUR LA CONNEXION A BARKBANK ***** 
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public final class CreditCardServiceTest implements CreditCardStatus {
		
	@Autowired
	private CreditCardService service;
		
    //==================================
    //=            Test cases          =
    //==================================
    /**
     * This method checks a valid credit card.
     */
    @Test
    public void testVerifyValidCreditCard() {
    	CreditCard validCreditCard = new CreditCard();
    	validCreditCard.setCreditCardNumber("4564 1231 4564 2222");
    	validCreditCard.setCreditCardExpiryDate("10/23");
    	validCreditCard.setCreditCardType("Visa");
        try {
        	service.verifyCreditCard(validCreditCard);
        } catch (CheckException e) {
        	fail("Credit card is valid");
        }
    }

    /**
     * This method checks a credit card with an old date.
     */
    @Test
    public void testVerifyTooOldCreditCard() {
    	CreditCard creditCard = new CreditCard();
    	creditCard.setCreditCardNumber("4564 1231 4564 2222");
    	creditCard.setCreditCardExpiryDate("01/01");
    	creditCard.setCreditCardType("Visa");
        try {
        	service.verifyCreditCard(creditCard);
        	fail("Credit card is too old");
        } catch (CheckException e) {
            String assertMessage = "Credit card is too old. The exception messsage should be " + INVALID_DATE;
            assertEquals(assertMessage, INVALID_DATE, e.getMessage());
        }
    }

    /**
     * This method checks a credit card with a month > 12.
     */
    @Test
    public void testCreditCardWithInvalidMonth() throws Exception {
    	CreditCard creditCard = new CreditCard();
    	creditCard.setCreditCardNumber("4564 1231 4564 2222");
    	creditCard.setCreditCardExpiryDate("99/25");
    	creditCard.setCreditCardType("Visa");
        try {
        	service.verifyCreditCard(creditCard);
        	fail("Credit card has invalid month");
        } catch (CheckException e) {
            String assertMessage = "Credit card has invalid month. The exception messsage should be " + INVALID_DATE;
            assertEquals(assertMessage, INVALID_DATE, e.getMessage());
        }
    }

    /**
     * This method checks a credit card with a bad format for the year.
     */
    @Test
    public void testVerifyCreditCardWithInvalidYearFormat() throws Exception {
    	CreditCard creditCard = new CreditCard();
    	creditCard.setCreditCardNumber("4564 1231 4564 2222");
    	creditCard.setCreditCardExpiryDate("01/deux mille trente");
    	creditCard.setCreditCardType("Visa");
        try {
        	service.verifyCreditCard(creditCard);
        	fail("Credit card has invalid year format");
        } catch (CheckException e) {
            String assertMessage = "Credit card has invalid year format. The exception messsage should be " + INVALID_CREDIT_CARD;
            assertEquals(assertMessage, INVALID_CREDIT_CARD, e.getMessage());
        }
    }

    /**
     * This method checks a credit card with an invalid number for a visa.
     */
    @Test
    public void testVerifyCreditCardWithInvalidNumber() throws Exception {
    	CreditCard creditCard = new CreditCard();
    	creditCard.setCreditCardNumber("4564 1231 4564 1111");
    	creditCard.setCreditCardExpiryDate("10/23");
    	creditCard.setCreditCardType("Visa");
        try {
        	service.verifyCreditCard(creditCard);
        	fail("Credit card number is wrong");
        } catch (CheckException e) {
            String assertMessage = "Credit card number is wrong. The exception messsage should be " + INVALID_NUMBER;
            assertEquals(assertMessage, INVALID_NUMBER, e.getMessage());
        }
    }
    
}