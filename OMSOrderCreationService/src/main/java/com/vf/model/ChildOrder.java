package com.vf.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "childorder")
public class ChildOrder {
	@Id
	@GeneratedValue
	private Long id;
	@Column(insertable = false, updatable = false, nullable = false)
	private Long master_id;

	@ManyToOne
	 @JoinColumn(name = "master_id")
	private MasterOrder masterOrder;
	 
	@NotBlank
	private String mobileNumber;
	@NotBlank
	private String product;
	@NotBlank
	private String address;
	@NotBlank
	private String orderStatus;

	
	public ChildOrder() {
		super();
	}

	public Long getMaster_id() {
		return master_id;
	}

	public void setMaster_id(Long master_id) {
		this.master_id = master_id;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MasterOrder getMasterOrder() {
		return masterOrder;
	}

	public void setMasterOrder(MasterOrder masterOrder) {
		this.masterOrder = masterOrder;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

}