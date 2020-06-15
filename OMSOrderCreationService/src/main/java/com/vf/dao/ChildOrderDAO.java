package com.vf.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vf.exception.ChildOrderNotFoundException;
import com.vf.model.ChildOrder;
import com.vf.repository.ChildOrderRepository;
import com.vf.util.Constants;

@Component
public class ChildOrderDAO {

	@Autowired
	ChildOrderRepository OrderRepository;

	// Get All Orders
	public List<ChildOrder> getAllOrders() {
		return OrderRepository.findAll();
	}

	// Create a new Order
	public ChildOrder createOrder(ChildOrder order) {
		return OrderRepository.save(order);
	}

	// Pick a single Order
	public ChildOrder getOrderById(Long id) throws ChildOrderNotFoundException {
		return OrderRepository.findById(id).orElseThrow(() -> new ChildOrderNotFoundException(id));
	}

	// Update a Order
	public ChildOrder updateOrderStatus(ChildOrder order) throws ChildOrderNotFoundException {

		ChildOrder updatedOrder = OrderRepository.save(order);

		return updatedOrder;
	}

	public ChildOrder pickOpenOrderForSubmissionToOMS(String orderStatus) throws ChildOrderNotFoundException {

		List<ChildOrder> childOrderList = (ArrayList<ChildOrder>)OrderRepository.findChildOrderByOrderStatus(orderStatus);
		ChildOrder order = childOrderList.get(0);
		order.setOrderStatus(Constants.CREATING_STATUS);
		order.setLastUpdateDate(new Date());
		OrderRepository.save(order);
		return order;
	}

	// Delete a Order
	public boolean deleteOrder(Long id) throws ChildOrderNotFoundException {
		ChildOrder order = OrderRepository.findById(id).orElseThrow(() -> new ChildOrderNotFoundException(id));

		OrderRepository.delete(order);

		return true;
	}
}