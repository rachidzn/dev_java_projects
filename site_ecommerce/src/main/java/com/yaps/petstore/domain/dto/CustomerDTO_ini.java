package com.yaps.petstore.domain.dto;

import java.io.Serializable;

/**
 * This class follows the Data Transfert Object design pattern.
 * It is a client view of a Customer. This class only
 * transfers data from a distant service to a client.
 */
@SuppressWarnings("serial")
public final class CustomerDTO_ini implements Serializable {

    // ======================================
    // =             Attributes             =
    // ======================================
    private String id;
    private String firstname;
    private String lastname;
    private String telephone;
    private String email;
    private final AddressDTO address = new AddressDTO();
    private final CreditCardDTO creditCard = new CreditCardDTO();

    // ======================================
    // =            Constructors            =
    // ======================================
    public CustomerDTO_ini() {
    }

    public CustomerDTO_ini(final String id, final String firstname, final String lastname) {
        setId(id);
        setFirstname(firstname);
        setLastname(lastname);
    }

    // ======================================
    // =         Getters and Setters        =
    // ======================================
    public String getId() {
        return id;
    }

    public void setId(final String id) {
    	this.id = id;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(final String telephone) {
    	this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
    	this.email = email;
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

    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("CustomerDTO{");
        buf.append("id=").append(getId());
        buf.append(",firstname=").append(getFirstname());
        buf.append(",lastname=").append(getLastname());
        buf.append(",telephone=").append(getTelephone());
        buf.append(",email=").append(getEmail());
        buf.append(",street1=").append(getStreet1());
        buf.append(",street2=").append(getStreet2());
        buf.append(",city=").append(getCity());
        buf.append(",state=").append(getState());
        buf.append(",zipcode=").append(getZipcode());
        buf.append(",country=").append(getCountry());
        buf.append(",creditCardNumber=").append(getCreditCardNumber());
        buf.append(",creditCardType=").append(getCreditCardType());
        buf.append(",creditCardExpiryDate=").append(getCreditCardExpiryDate());
        buf.append('}');
        return buf.toString();
    }
}
