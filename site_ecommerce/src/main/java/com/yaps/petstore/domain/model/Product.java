package com.yaps.petstore.domain.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.yaps.petstore.exception.CheckException;

/**
 * This class represents a Product in the catalog of the YAPS company.
 * The catalog is divided into categories. Each one divided into products
 * and each product in items.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "T_PRODUCT") 
public final class Product implements Serializable {

    // ======================================
    // =             Attributes             =
    // ======================================
	@Id
	private String id;
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(name="category_fk")
    private Category category;

    // ======================================
    // =            Constructors            =
    // ======================================
    public Product() {}

    public Product(final String id) {
        setId(id);
    }

    public Product(final String id, final String name, final String description, final Category category) {
        setId(id);
        setName(name);
        setDescription(description);
        setCategory(category);
    }

    // ======================================
    // =           Business methods         =
    // ======================================
    public void checkData() throws CheckException {
        if (getName() == null || "".equals(getName()))
            throw new CheckException("Invalid name");
        if (getDescription() == null || "".equals(getDescription()))
            throw new CheckException("Invalid description");
        if (getCategory() == null || getCategory().getId() == null || "".equals(getCategory().getId()))
            throw new CheckException("Invalid category");
    }

    // ======================================
    // =         Getters and Setters        =
    // ======================================
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
        return name;
    }

    public void setName(final String name) {
    	this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
    	this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(final Category category) {
        this.category = category;
    }

    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("\n\tProduct {");
        buf.append("\n\t\tId=").append(getId());
        buf.append("\n\t\tName=").append(getName());
        buf.append("\n\t\tDescription=").append(getDescription());
        buf.append("\n\t\tCategory Id=").append(getCategory().getId());
        buf.append("\n\t\tCategory Name=").append(getCategory().getName());
        buf.append("\n\t}");
        return buf.toString();
    }

}
