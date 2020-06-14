package com.vf.util;

public class Constants {

	
	public static String OPEN_STATUS = "OPEN";
	public static String INPROGRESS_STATUS = "INPROGRESS";
	public static String COMPLETED_STATUS = "COMPLETED";
	public static String FAILED_STATUS = "FAILED";
	public static String FAILED_RESP = "ERROR";
	public static String SUCCESS_RESP = "SUCCESS";
	public static String CHILD_EXCLUDE_XPATH = "//SubscriberList";
	public static String CHILD_XPATH = "/CustomerOrder/SubscriberList/Subscriber[MobileNumber/text()='REPLACE']";
	public static String CHILD_LIST_XPATH = "//Subscriber";
	public static String REPLACE_STRING = "REPLACE";
	
	public static String MASTER_ORDER_REF_XPATH = "/CustomerOrder/OrderRefNumber";
	public static String MASTER_CUST_NAME_XPATH = "/CustomerOrder/CustomerName";
	public static String MASTER_CUST_ACCT_ID_XPATH = "/CustomerOrder/CustomerAccountId";
	public static String CHILD_SUBSCRIBER_TAG_NAME = "SubscriberName";
	public static String CHILD_MOBILE_TAG_NAME = "MobileNumber";
}
