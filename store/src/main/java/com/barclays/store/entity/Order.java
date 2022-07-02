package com.barclays.store.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Order {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer orderId;

	private Integer orderNumber;
	
	
	private LocalDateTime dateOfOrder;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="PRODUCT_ID")
	private Product product;
	
	private Integer quantity;
	
	private Double totalPrice;
	
	private String address;
	
	private String orderStatus;

	private String paymentThrough;
	
	private LocalDateTime dataOfDelivery;
	
	
}
