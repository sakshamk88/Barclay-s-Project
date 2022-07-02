package com.barclays.store.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private Integer productId;

    @Column(name="product_name")
    private String name;

    @Column(name="maximum_retail_price")
    private Float mrp;

    @Column(name="discount_percentage")
    private Float discountPercentage;

    @Column(name="available_quantity")
    private Integer availableQuantity;

    @Column(name="discount_selling_price")
    private Float discountSellingPrice;

    @Column(name="weight")
    private Float weightInGms;

    @Column(name="out_of_stock")
    private Boolean outOfStock;

    @Column(name="quantity")
    private Integer quantity;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getMrp() {
		return mrp;
	}

	public void setMrp(Float mrp) {
		this.mrp = mrp;
	}

	public Float getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(Float discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public Integer getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(Integer availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	public Float getDiscountSellingPrice() {
		return discountSellingPrice;
	}

	public void setDiscountSellingPrice(Float discountSellingPrice) {
		this.discountSellingPrice = discountSellingPrice;
	}

	public Float getWeightInGms() {
		return weightInGms;
	}

	public void setWeightInGms(Float weightInGms) {
		this.weightInGms = weightInGms;
	}

	public Boolean getOutOfStock() {
		return outOfStock;
	}

	public void setOutOfStock(Boolean outOfStock) {
		this.outOfStock = outOfStock;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
    
    

}
