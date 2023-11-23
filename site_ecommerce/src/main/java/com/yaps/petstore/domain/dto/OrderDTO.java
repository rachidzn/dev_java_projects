package com.yaps.petstore.domain.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * This class follows the Data Transfert Object design pattern.
 * It is a client view of an Order. This class only
 * transfers data from a distant service to a client.
 */
@SuppressWarnings("serial")
public final class OrderDTO implements Serializable {

    // ======================================
    // =             Attributes             =
    // ======================================
    private long id;
    private Date orderDate;
    private String firstname;
    private String lastname;
    private final AddressDTO address = new AddressDTO();
    private final CreditCardDTO creditCard = new CreditCardDTO();
    private Collection<OrderLineDTO> orderLines;
    private String customerId;

    // ======================================
    // =            Constructors            =
    // ======================================
    public OrderDTO() {
    }

    public OrderDTO(final String firstname, final String lastname, final String street1, final String city, final String zipcode, final String country) {
        setFirstname(firstname);
        setLastname(lastname);
        setStreet1(street1);
        setCity(city);
        setZipcode(zipcode);
        setCountry(country);
    }

    // ======================================
    // =         Getters and Setters        =
    // ======================================
    public long getId() {
        return id;
    }

    public void setId(final long id) {
    	this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(final Date orderDate) {
    	this.orderDate = orderDate;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(final String firstname) {
    	this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(final String lastname) {
    	this.lastname = lastname;
    }

    public String getStreet1() {
        return address.getStreet1();
    }

    public void setStreet1(final String street1) {
        address.setStreet1(street1);
    }

    public String getStreet2() {
        return address.getStreet2();
    }

    public void setStreet2(final String street2) {
        address.setStreet2(street2);
    }

    public String getCity() {
        return address.getCity();
    }

    public void setCity(final String city) {
        address.setCity(city);
    }

    public String getState() {
        return address.getState();
    }

    public void setState(final String state) {
        address.setState(state);
    }

    public String getZipcode() {
        return address.getZipcode();
    }

    public void setZipcode(final String zipcode) {
        address.setZipcode(zipcode);
    }

    public String getCountry() {
        return address.getCountry();
    }

    public void setCountry(final String country) {
        address.setCountry(country);
    }

    public String getCreditCardNumber() {
        return creditCard.getCreditCardNumber();
    }

    public void setCreditCardNumber(final String creditCardNumber) {
        creditCard.setCreditCardNumber(creditCardNumber);
    }

    public String getCreditCardType() {
        return creditCard.getCreditCardType();
    }

    public void setCreditCardType(final String creditCardType) {
        creditCard.setCreditCardType(creditCardType);
    }

    public String getCreditCardExpiryDate() {
        return creditCard.getCreditCardExpiryDate();
    }

    public void setCreditCardExpiryDate(final String creditCardExpiryDate) {
        creditCard.setCreditCardExpiryDate(creditCardExpiryDate);
    }

    public Collection<OrderLineDTO> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(final Collection<OrderLineDTO> orderLines) {
    	this.orderLines = orderLines;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(final String customerId) {
    	this.customerId = customerId;
    }

    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("OrderDTO{");
        buf.append("id=").append(getId());
        buf.append(",orderDate=").append(getOrderDate());
        buf.append(",firstname=").append(getFirstname());
        buf.append(",lastname=").append(getLastname());
        buf.append(",street1=").append(getStreet1());
        buf.append(",street2=").append(getStreet2());
        buf.append(",city=").append(getCity());
        buf.append(",state=").append(getState());
        buf.append(",zipcode=").append(getZipcode());
        buf.append(",country=").append(getCountry());
        buf.append(",creditCardNumber=").append(getCreditCardNumber());
        buf.append(",creditCardType=").append(getCreditCardType());
        buf.append(",creditCardExpiry Date=").append(getCreditCardExpiryDate());
        buf.append(",customerId=").append(getCustomerId());
        buf.append(",[orderLines=").append(getOrderLines()).append(']');
        buf.append('}');
        return buf.toString();
    }
}
