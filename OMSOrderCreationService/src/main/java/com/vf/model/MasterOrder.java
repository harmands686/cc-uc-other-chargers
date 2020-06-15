package com.vf.model;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "masterorder" , uniqueConstraints={@UniqueConstraint(columnNames={"orderRefNumber"})})
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

	@Temporal(value= TemporalType.TIMESTAMP)
    private Date creationDate;
	
	@Temporal(value= TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
	
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	
	public MasterOrder() {
		super();
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