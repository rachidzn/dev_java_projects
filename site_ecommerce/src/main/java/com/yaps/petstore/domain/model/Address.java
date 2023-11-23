package com.yaps.petstore.domain.model;

import javax.persistence.Embeddable;
import com.yaps.petstore.exception.CheckException;

@Embeddable
public class Address {
	
	// ======================================
    // =             Attributes             =
    // ======================================
	private String street1;
	private String street2;
	private String city;
	private String state;
	private String zipcode;
	private String country;
	
	public Address() {}

	// ======================================
    // =           Business methods         =
    // ======================================
    public void checkData() throws CheckException {
        if (street1 == null || "".equals(street1))
            throw new CheckException("Invalid street");
        if (city == null || "".equals(city))
            throw new CheckException("Invalid city");
        if (zipcode == null || "".equals(city))
            throw new CheckException("Invalid zipcode");
        if (country == null || "".equals(country))
            throw new CheckException("Invalid country");
    }
	// ======================================
    // =         Getters and Setters        =
    // ======================================
	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
}