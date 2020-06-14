package com.vf.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Subscriber")
@XmlAccessorType(XmlAccessType.FIELD)
public class Subscriber {

	@XmlElement(name="SubscriberName")
	private String subscriberName;
	
	@XmlElement(name="MobileNumber")
	private String mobileNumber;
	
	@XmlElement(name="Product")
	private String product;
	
	@XmlElement(name="Address")
	private String address;

	public Subscriber() {
		super();
	}

	
	
	public String getSubscriberName() {
		return subscriberName;
	}



	public void setSubscriberName(String subscriberName) {
		this.subscriberName = subscriberName;
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



	@Override
	public String toString() {
		return "Subscriber [subscriberName=" + subscriberName + ", mobileNumber=" + mobileNumber + ", product="
				+ product + ", address=" + address + "]";
	}

	
}