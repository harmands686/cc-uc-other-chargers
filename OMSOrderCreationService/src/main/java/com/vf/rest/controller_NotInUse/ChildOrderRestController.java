package com.vf.rest.controller_NotInUse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vf.exception.ChildOrderNotFoundException;
import com.vf.exception.MasterOrderNotFoundException;
import com.vf.model.ChildOrder;
import com.vf.repository.ChildOrderRepository;
import com.vf.util.Constants;

@RestController
public class ChildOrderRestController {

	@Autowired
	ChildOrderRepository OrderRepository;

	// Get All Orders
	@GetMapping("/childorders")
	public List<ChildOrder> getAllOrders() {
		return OrderRepository.findAll();
	}
	
	@GetMapping("/hello2")
	public String sayhello() {
		return "Welcome2";
	}

	// Create a new Order
	@PostMapping("/childorders")
	public ChildOrder createOrderOrder(@Valid @RequestBody ChildOrder order) {
		return OrderRepository.save(order);
	}

	// Get a Single Order
	@GetMapping("/childorders/{id}")
	public ChildOrder getOrderById(@PathVariable(value = "id") Long orderId) throws ChildOrderNotFoundException {
		return OrderRepository.findById(orderId).orElseThrow(() -> new ChildOrderNotFoundException(orderId));
	}
	
	// Get a Order list
	@GetMapping("/childordersbyrefnumber/{id}")
	public List<ChildOrder> findChildOrderByOrderRefNumber(@PathVariable(value = "id") String orderRefNumber) throws ChildOrderNotFoundException {
		List<ChildOrder> childOrderList = (ArrayList<ChildOrder>)OrderRepository.findChildOrderByOrderRefNumber(orderRefNumber);
		return childOrderList;
	}

	// Delete a Order
	@DeleteMapping("/childorders/{id}")
	public ResponseEntity<?> deleteOrder(@PathVariable(value = "id") Long orderId) throws ChildOrderNotFoundException {
		ChildOrder order = OrderRepository.findById(orderId)
				.orElseThrow(() -> new ChildOrderNotFoundException(orderId));

		OrderRepository.delete(order);

		return ResponseEntity.ok().build();
	}
}