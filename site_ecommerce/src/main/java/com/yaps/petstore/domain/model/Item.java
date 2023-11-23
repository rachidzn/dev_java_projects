package com.yaps.petstore.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.yaps.petstore.exception.CheckException;

/**
 * This class represents an Item in the catalog of the YAPS company.
 * The catalog is divided into categories. Each one divided into products
 * and each product in items.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "T_ITEM") 
public final class Item implements Serializable {

    // ======================================
    // =             Attributes             =
    // ======================================
	@Id
	private String id;
    private String name;
    @Column(name="UNIT_COST")
    private double unitCost;
    @Column(name="IMAGE_PATH")
    private String imagePath;
    @ManyToOne
    @JoinColumn(name="product_fk")
    private Product product;

    // ======================================
    // =            Constructors            =
    // ======================================
    public Item() {}

    public Item(final String id) {
        setId(id);
    }

    public Item(final String id, final String name, final double unitCost, final Product product) {
        setId(id);
        setName(name);
        setUnitCost(unitCost);
        setProduct(product);
    }

    // ======================================
    // =           Business methods         =
    // ======================================
    public void checkData() throws CheckException {
        if (getName() == null || "".equals(getName()))
            throw new CheckException("Invalid name");
        if (getUnitCost() <= 0)
            throw new CheckException("Invalid unit cost");
        if (getProduct() == null || getProduct().getId() == null || "".equals(getProduct().getId()))
            throw new CheckException("Invalid product");
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

    public double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(final double unitCost) {
    	this.unitCost = unitCost;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(final String imagePath) {
    	this.imagePath = imagePath;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(final Product product) {
    	this.product = product;
    }

    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("\n\tItem {");
        buf.append("\n\t\tId=").append(getId());
        buf.append("\n\t\tName=").append(getName());
        buf.append("\n\t\tUnit Cost=").append(getUnitCost());
        buf.append("\n\t\timagePath=").append(getImagePath());
        buf.append("\n\t\tProduct Id=").append(getProduct().getId());
        buf.append("\n\t\tProduct Name=").append(getProduct().getName());
        buf.append("\n\t}");
        return buf.toString();
    }
}
