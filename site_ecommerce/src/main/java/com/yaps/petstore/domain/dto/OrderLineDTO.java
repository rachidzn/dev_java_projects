package com.yaps.petstore.domain.dto;

import java.io.Serializable;

/**
 * This class follows the Data Transfert Object design pattern.
 * It is a client view of an Order Line. This class only
 * transfers data from a distant service to a client.
 */
@SuppressWarnings("serial")
public final class OrderLineDTO implements Serializable {

    // ======================================
    // =             Attributes             =
    // ======================================
    private int quantity;
    private double unitCost;
    private String itemId;
    private String itemName;
    private long orderId;

    // ======================================
    // =            Constructors            =
    // ======================================
    public OrderLineDTO() {
    }

    public OrderLineDTO(final int quantity, final double unitCost) {
        setQuantity(quantity);
        setUnitCost(unitCost);
    }

    // ======================================
    // =         Getters and Setters        =
    // ======================================
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(final int quantity) {
    	this.quantity = quantity;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(final double unitCost) {
    	this.unitCost = unitCost;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(final String itemId) {
    	this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(final String itemName) {
    	this.itemName = itemName;
    }
    
    public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("OrderLineDTO{");
        buf.append("quantity=").append(getQuantity());
        buf.append(",unitCost=").append(getUnitCost());
        buf.append(",itemId=").append(getItemId());
        buf.append(",itemName=").append(getItemName());
        buf.append('}');
        return buf.toString();
    }
}
