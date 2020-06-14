package com.vf.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="CustomerOrder")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerOrder {

	@XmlElement(name="OrderRefNumber")
	private String orderRefNumber;
	
	@XmlElement(name="CustomerName")
	private String customerName;

	@XmlElement(name="CustomerAccountId")
	private String customerAccountId;

	@XmlElementWrapper(name = "SubscriberList") 
	@XmlElement(name="Subscriber")
	private List<Subscriber> subscriberList;

	
	
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



	public List<Subscriber> getSubscriberList() {
		return subscriberList;
	}



	public void setSubscriberList(List<Subscriber> subscriberList) {
		this.subscriberList = subscriberList;
	}



	@Override
	public String toString() {
		return "CustomerOrder [orderRefNumber=" + orderRefNumber + ", customerName=" + customerName
				+ ", customerAccountId=" + customerAccountId + ", subscriberList=" + subscriberList + "]";
	}

	
}