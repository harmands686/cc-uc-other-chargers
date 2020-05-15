package com.vf.rest.controller_NotInUse;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vf.exception.MasterOrderNotFoundException;
import com.vf.model.ChildOrder;
import com.vf.repository.ChildOrderRepository;

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
    public ChildOrder getOrderById(@PathVariable(value = "id") Long orderId) throws MasterOrderNotFoundException {
        return OrderRepository.findById(orderId)
                .orElseThrow(() -> new MasterOrderNotFoundException(orderId));
    }

// Update a Order
    @PutMapping("/childorders/{id}")
    public ChildOrder updateOrderStatus(@PathVariable(value = "id") Long orderId,
                           @Valid @RequestBody ChildOrder orderDetails) throws MasterOrderNotFoundException {

ChildOrder order = OrderRepository.findById(orderId)
                .orElseThrow(() -> new MasterOrderNotFoundException(orderId));

order.setMobileNumber(orderDetails.getMobileNumber());
order.setAddress(orderDetails.getAddress());
order.setProduct(orderDetails.getProduct());
order.setOrderStatus(orderDetails.getOrderStatus());

ChildOrder updatedOrder = OrderRepository.save(order);

return updatedOrder;
    }

// Delete a Order
    @DeleteMapping("/childorders/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable(value = "id") Long orderId) throws MasterOrderNotFoundException {
        ChildOrder order = OrderRepository.findById(orderId)
                .orElseThrow(() -> new MasterOrderNotFoundException(orderId));

OrderRepository.delete(order);

return ResponseEntity.ok().build();
    }
}