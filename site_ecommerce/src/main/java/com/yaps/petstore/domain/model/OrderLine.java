package com.yaps.petstore.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.yaps.petstore.exception.CheckException;

/**
 * An Order has several order lines. This class represent one order line.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "T_ORDER_LINE") 
public final class OrderLine implements Serializable {

    // ======================================
    // =             Attributes             =
    // ======================================
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="order_line_seq_gen")
	private Long id;
    private int quantity;
    @Column(name="UNIT_COST")
    private double unitCost;
    @ManyToOne
    @JoinColumn(name ="item_fk", nullable = false)
    private Item item;
    @ManyToOne
    @JoinColumn(name ="order_fk", nullable = false)
    private Order order;

    // ======================================
    // =            Constructors            =
    // ======================================
    public OrderLine() {}

    public OrderLine(final int quantity, final double unitCost, final Order order, final Item item) {
        setQuantity(quantity);
        setUnitCost(unitCost);
        setOrder(order);
        setItem(item);
    }

    // ======================================
    // =           Business methods         =
    // ======================================
    public void checkData() throws CheckException {
        if (getUnitCost() <= 0)
            throw new CheckException("Invalid unit cost");
        if (getQuantity() <= 0)
            throw new CheckException("Invalid quantity");
        if (getOrder() == null)
            throw new CheckException("Invalid order");
        if (getItem() == null)
            throw new CheckException("Invalid item");
    }

    // ======================================
    // =         Getters and Setters        =
    // ======================================
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

    public Order getOrder() {
        return order;
    }

    public void setOrder(final Order order) {
    	this.order = order;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(final Item item) {
    	this.item = item;
    }

    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("\nOrderLine {");
        buf.append("\n\tId=").append(getId());
        buf.append("\n\tQuantity=").append(getQuantity());
        buf.append("\n\tUnit Cost=").append(getUnitCost());
        buf.append("\n\tItem Id=").append(getItem().getId());
        buf.append("\n\tItem Name=").append(getItem().getName());
        buf.append("\n}");
        return buf.toString();
    }
}
