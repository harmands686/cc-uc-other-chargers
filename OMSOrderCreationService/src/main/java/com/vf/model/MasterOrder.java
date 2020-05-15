package com.vf.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "masterorder")
public class MasterOrder {
	@Id
	@GeneratedValue
	private Long id;
	@NotBlank
	private String orderRefNumber;
	@NotBlank
	private String customerName;
	@NotBlank
	private String customerAccountId;
	@NotBlank
	private String orderStatus;

	public MasterOrder() {
		super();
	}

	public MasterOrder(Long id, String orderRefNumber, String customerName, String customerAccountId,
			String orderStatus) {
		super();
		this.id = id;
		this.orderRefNumber = orderRefNumber;
		this.customerName = customerName;
		this.customerAccountId = customerAccountId;
		this.orderStatus = orderStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderRefNumber() {
		return orderRefNumber;
	}

	public void setOrderRefNumber(String orderRefNumber) {
		this.orderRefNumber = orderRefNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAccountId() {
		return customerAccountId;
	}

	public void setCustomerAccountId(String customerAccountId) {
		this.customerAccountId = customerAccountId;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

}