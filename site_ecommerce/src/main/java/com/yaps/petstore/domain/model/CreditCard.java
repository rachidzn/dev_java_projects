package com.yaps.petstore.domain.model;

import java.io.IOException;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This class encapsulates all the data for a credit card.
 *
 * @see com.yaps.petstore.authentication.domain.model.User.domain.customer.Customer
 * @see com.yaps.petstore.server.domain.order.Order
 */
@Embeddable
public final class CreditCard {

    // ======================================
    // =             Attributes             =
    // ======================================
	@Column(name="CREDIT_CARD_NUMBER")
    private String creditCardNumber;
	@Column(name="CREDIT_CARD_TYPE")
    private String creditCardType;
	@Column(name="CREDIT_CARD_EXPIRY_DATE")
    private String creditCardExpiryDate;
    
    public CreditCard () {}
    
 // ======================================
    // =         Business methods        =
    // ======================================
    public String toJsonString() {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonString = null;
    	try {
            jsonString = mapper.writeValueAsString(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    	return jsonString;
    }
    
    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("CreditCard{");
        buf.append("creditCardNumber=").append(getCreditCardNumber());
        buf.append(",creditCardType=").append(getCreditCardType());
        buf.append(",creditCardExpiryDate=").append(getCreditCardExpiryDate());
        buf.append('}');
        return buf.toString();
    }
    
    // ======================================
    // =         Getters and Setters        =
    // ======================================

	public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(final String creditCardNumber) {
    	this.creditCardNumber = creditCardNumber;
    }

    public String getCreditCardType() {
        return creditCardType;
    }

    public void setCreditCardType(final String creditCardType) {
    	this.creditCardType = creditCardType;
    }

    public String getCreditCardExpiryDate() {
        return creditCardExpiryDate;
    }

    public void setCreditCardExpiryDate(final String creditCardExpiryDate) {
        this.creditCardExpiryDate = creditCardExpiryDate;
    }

}
