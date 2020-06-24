package com.vf.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
	private String orderRefNumber;

	@NotBlank
	private String mobileNumber;
	
	@NotBlank
	private String orderStatus;

	@Temporal(value= TemporalType.TIMESTAMP)
    private Date creationDate;
	
	@Temporal(value= TemporalType.TIMESTAMP)
    private Date omsOrderCreationDate;
	
	@Temporal(value= TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
	
	@Column(name="xml", length=5000)
	private String payload;
	
	private String omsOrderId;
	
	
	
	public ChildOrder() {
		super();
	}
	

	
	
	public String getOrderRefNumber() {
		return orderRefNumber;
	}




	public void setOrderRefNumber(String orderRefNumber) {
		this.orderRefNumber = orderRefNumber;
	}




	public String getOmsOrderId() {
		return omsOrderId;
	}




	public void setOmsOrderId(String omsOrderId) {
		this.omsOrderId = omsOrderId;
	}




	public Date getCreationDate() {
		return creationDate;
	}



	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}



	public Date getOmsOrderCreationDate() {
		return omsOrderCreationDate;
	}



	public void setOmsOrderCreationDate(Date omsOrderCreationDate) {
		this.omsOrderCreationDate = omsOrderCreationDate;
	}



	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getPayload() {
		return payload;
	}


	public void setPayload(String payload) {
		this.payload = payload;
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


	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

}