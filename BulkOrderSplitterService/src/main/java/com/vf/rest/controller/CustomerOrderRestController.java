package com.vf.rest.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.vf.model.ChildOrder;
import com.vf.model.MasterOrder;
import com.vf.repository.ChildOrderRepository;
import com.vf.repository.MasterOrderRepository;
import com.vf.util.Constants;
import com.vf.util.XMLUtil;

@RestController
public class CustomerOrderRestController {

	@Autowired
	MasterOrderRepository masterOrderRepo;

	@Autowired
	ChildOrderRepository childOrderRepo;

	// Create Order
	@PostMapping(value = "/customerorder", consumes = MediaType.APPLICATION_XML_VALUE)
	public String processCustomerOrder(HttpServletRequest request) {

		MasterOrder masterOrder = null;
		ChildOrder childOrder = null;
		try {
			String inputXml = XMLUtil.extractPostRequestBody(request);
			masterOrder = populateMasterOrder(inputXml);
			masterOrderRepo.save(masterOrder);
			NodeList childOrderList = XMLUtil.xPathProcessor(Constants.CHILD_LIST_XPATH, inputXml);
			System.out.println("Child orders count>>>>>>" + childOrderList.getLength());
			for (int i = 0; i < childOrderList.getLength(); i++) {
				childOrder = populateChildOrder(masterOrder, childOrderList.item(i), inputXml);
				childOrderRepo.save(childOrder);
			}
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return masterOrder.getId().toString();
	}

	public MasterOrder populateMasterOrder(String xml) throws XPathExpressionException {

		MasterOrder masterOrder = new MasterOrder();
		masterOrder.setOrderRefNumber(XMLUtil.xpathProcessor_V(Constants.MASTER_ORDER_REF_XPATH, xml));
		masterOrder.setCustomerAccountId(XMLUtil.xpathProcessor_V(Constants.MASTER_CUST_ACCT_ID_XPATH, xml));
		masterOrder.setCustomerName(XMLUtil.xpathProcessor_V(Constants.MASTER_CUST_NAME_XPATH, xml));
		masterOrder.setCreationDate(new Date());
		masterOrder.setLastUpdateDate(new Date());
		masterOrder.setOrderStatus(Constants.OPEN_STATUS);
		return masterOrder;
	}

	public ChildOrder populateChildOrder(MasterOrder masterOrder, Node child, String xml)
			throws XPathExpressionException, TransformerException, ParserConfigurationException, SAXException,
			IOException {

		ChildOrder childOrder = new ChildOrder();
		Element element = (Element) child;
		String mobileNumber = element.getElementsByTagName(Constants.CHILD_MOBILE_TAG_NAME).item(0).getTextContent();
		System.out.println("Mobile>>>>>" + mobileNumber);
		String calculatedChildXML = XMLUtil.childOrderXML(Constants.CHILD_EXCLUDE_XPATH,
				Constants.CHILD_XPATH.replace(Constants.REPLACE_STRING, mobileNumber), xml);
		childOrder.setMasterOrder(masterOrder);
		childOrder.setMaster_id(masterOrder.getId());
		childOrder.setMobileNumber(mobileNumber);
		childOrder.setCreationDate(new Date());
		childOrder.setPayload(calculatedChildXML);
		childOrder.setOrderStatus(Constants.OPEN_STATUS);
		childOrder.setOrderRefNumber(masterOrder.getOrderRefNumber());
		return childOrder;
	}

	/*
	 * @PostMapping(value="/customerorder", consumes =
	 * MediaType.APPLICATION_XML_VALUE) public String
	 * processCustomerOrder(@RequestBody CustomerOrder cust) {
	 * 
	 * try{
	 * 
	 * System.out.println(cust); MasterOrder master = new MasterOrder();
	 * master.setCustomerAccountId(cust.getCustomerAccountId());
	 * master.setCustomerName(cust.getCustomerName());
	 * master.setOrderRefNumber(cust.getOrderRefNumber());
	 * master.setOrderStatus(Constants.OPEN_STATUS);
	 * 
	 * masterOrderRepo.save(master);
	 * 
	 * for (Subscriber subscriber : cust.getSubscriberList()) { ChildOrder
	 * childOrder = new ChildOrder();
	 * childOrder.setAddress(subscriber.getAddress());
	 * childOrder.setMobileNumber(subscriber.getMobileNumber());
	 * childOrder.setProduct(subscriber.getProduct());
	 * childOrder.setSubscriberName(subscriber.getSubscriberName());
	 * childOrder.setOrderStatus(Constants.OPEN_STATUS);
	 * childOrder.setMasterOrder(master);
	 * childOrder.setMaster_id(master.getId()); childOrderRepo.save(childOrder);
	 * } }catch (Exception e) { return Constants.FAILED_RESP; // TODO: handle
	 * exception }
	 * 
	 * return Constants.SUCCESS_RESP; }
	 */

	@GetMapping("/hello3")
	public String sayhello() {
		return "Welcome3";
	}

}