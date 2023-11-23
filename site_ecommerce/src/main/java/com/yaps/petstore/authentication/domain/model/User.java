package com.yaps.petstore.authentication.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import com.yaps.petstore.domain.model.Address;
import com.yaps.petstore.domain.model.CreditCard;
import com.yaps.petstore.exception.CheckException;


/**
 * This class represents a customer for the YAPS company.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "T_USER") 
public final class User implements Serializable {

	// ======================================
    // =             Attributes             =
    // ======================================
	@Id
	@Column(name="id")
	private String username;
    private String firstname;
    private String lastname;
    private String telephone;
    private String email;
	@NotEmpty
	private String password;
    @Embedded
    private final Address address = new Address();
    @Embedded
    private final CreditCard creditCard = new CreditCard();
    @ManyToOne
	@JoinColumn(name = "role_fk", referencedColumnName="id")
	private Role role;

	// ======================================
    // =            Constructors            =
    // ======================================
    
    public User() {}

    public User(final String username) {
    	this.username=username;
    }

    public User(final String username, final String firstname, final String lastname) {
    	this.username=username;
    	this.firstname = firstname;
    	this.lastname = lastname;
    }

    // ======================================
    // =           Business methods         =
    // ======================================
    /**
     * This method checks the integrity of the object data.
     * @throws CheckException if data is invalid
     */
    public void checkData() throws CheckException {
        if (firstname == null || "".equals(firstname))
            throw new CheckException("Invalid customer first name");
        if (lastname == null || "".equals(lastname))
            throw new CheckException("Invalid customer last name");
    }

    // ======================================
    // =         Getters and Setters        =
    // ======================================
    
    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Address getAddress() {
		return address;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("\n\tCustomer {");
        buf.append("\n\t\tId=").append(getUsername());
        buf.append("\n\t\tFirst Name=").append(getFirstname());
        buf.append("\n\t\tLast Name=").append(getLastname());
        buf.append("\n\t\tTelephone=").append(getTelephone());
        buf.append("\n\t\temail=").append(getEmail());
        buf.append(",street1=").append(getStreet1());
        buf.append(",street2=").append(getStreet2());
        buf.append(",city=").append(getCity());
        buf.append(",state=").append(getState());
        buf.append(",zipcode=").append(getZipcode());
        buf.append(",country=").append(getCountry());
        buf.append(",creditCardNumber=").append(getCreditCardNumber());
        buf.append(",creditCardType=").append(getCreditCardType());
        buf.append(",creditCardExpiryDate=").append(getCreditCardExpiryDate());
        buf.append("\n\t}");
        return buf.toString();
    }
}
