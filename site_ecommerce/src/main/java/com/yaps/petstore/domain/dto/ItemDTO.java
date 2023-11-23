package com.yaps.petstore.domain.dto;

import java.io.Serializable;

/**
 * This class follows the Data Transfert Object design pattern.
 * It is a client view of an Item. This class only
 * transfers data from a distant service to a client.
 */
@SuppressWarnings("serial")
public final class ItemDTO implements Serializable {

    // ======================================
    // =             Attributes             =
    // ======================================
    private String id;
    private String name;
    private double unitCost;
    private String imagePath;
    private String productId;
    private String productName;
    private String productDescription;

    // ======================================
    // =            Constructors            =
    // ======================================
    public ItemDTO() {
    }

    public ItemDTO(final String id, final String name, final double unitCost) {
        setId(id);
        setName(name);
        setUnitCost(unitCost);
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

    public String getProductId() {
        return productId;
    }

    public void setProductId(final String productId) {
    	this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(final String productName) {
    	this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("ItemDTO{");
        buf.append("id=").append(getId());
        buf.append(",name=").append(getName());
        buf.append(",unitCost=").append(getUnitCost());
        buf.append(",imagePath=").append(getImagePath());
        buf.append(",productId=").append(getProductId());
        buf.append(",productName=").append(getProductName());
        buf.append(",productDescription=").append(getProductDescription());
        buf.append('}');
        return buf.toString();
    }
}
