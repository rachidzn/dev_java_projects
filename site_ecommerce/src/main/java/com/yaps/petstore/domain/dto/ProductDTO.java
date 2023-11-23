package com.yaps.petstore.domain.dto;

import java.io.Serializable;

/**
 * This class follows the Data Transfert Object design pattern.
 * It is a client view of a Product. This class only
 * transfers data from a distant service to a client.
 */
@SuppressWarnings("serial")
public final class ProductDTO implements Serializable {

    // ======================================
    // =             Attributes             =
    // ======================================
    private String id;
    private String name;
    private String description;
    private String categoryId;
    private String categoryName;

    // ======================================
    // =            Constructors            =
    // ======================================
    public ProductDTO() {
    }

    public ProductDTO(final String id, final String name, final String description) {
        setId(id);
        setName(name);
        setDescription(description);
    }

    // ======================================
    // =         Getters and Setters        =
    // ======================================
    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
    	this.description = description;
    }

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

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(final String categoryId) {
    	this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(final String categoryName) {
    	this.categoryName = categoryName;
    }

    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("ProductDTO{");
        buf.append("id=").append(getId());
        buf.append(",name=").append(getName());
        buf.append(",description=").append(getDescription());
        buf.append(",categoryId=").append(getCategoryId());
        buf.append(",categoryName=").append(getCategoryName());
        buf.append('}');
        return buf.toString();
    }
}
