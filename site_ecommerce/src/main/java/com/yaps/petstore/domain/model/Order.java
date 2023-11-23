package com.yaps.petstore.domain.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.yaps.petstore.authentication.domain.model.User;
import com.yaps.petstore.exception.CheckException;

/**
 * An order represents the items that a customer buys. This order has several
 * order items and is relevant for one customer. The order has address information
 * like the street, the city, the country... This is because a customer can order
 * a pet and wants it delivered at another adress. By default, the order address
 * is the same that the customer's one but it can be changed.
 *
 * @see User
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "T_ORDER") 
public final class Order implements Serializable {

    // ======================================
    // =             Attributes             =
    // ======================================
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="order_seq_gen")
	private Long id;
	@Column(name="ORDER_DATE")
    private Date orderDate;
    private String firstname;
    private String lastname;
    @Embedded
    private final Address address;
    @Embedded
    private final CreditCard creditCard = new CreditCard();
    @OneToOne
    @JoinColumn(name ="user_fk", nullable = false)
    private User customer;

    // ======================================
    // =            Constructors            =
    // ======================================
    public Order() {
    	 this.address=new Address();
    }

    public Order(final String firstname, final String lastname, final String street1, final String city, final String zipcode, final String country, final User customer) {
    	this.orderDate=new Date();
        this.address=new Address();
        setOrderDate(orderDate);
        setFirstname(firstname);
        setLastname(lastname);
        setStreet1(street1);
        setCity(city);
        setZipcode(zipcode);
        setCountry(country);
        setCustomer(customer);
    }

    // ======================================
    // =           Business methods         =
    // ======================================
    public void checkData() throws CheckException {
        if (firstname == null || "".equals(firstname))
            throw new CheckException("Invalid first name");
        if (lastname == null || "".equals(lastname))
            throw new CheckException("Invalid last name");
        address.checkData();
        if (customer == null)
            throw new CheckException("Invalid customer");
    }

    // ======================================
    // =         Getters and Setters        =
    // ======================================
    
    public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getOrderDate() {
        return orderDate;
    }

    private void setOrderDate(final Date orderDate) {
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

    public CreditCard getCreditCard() {
        return creditCard;
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

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(final User customer) {
    	this.customer = customer;
    }

    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("Order{");
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
        buf.append(",creditCardExpiryDate=").append(getCreditCardExpiryDate());
        buf.append('}');
        return buf.toString();
    }
}
