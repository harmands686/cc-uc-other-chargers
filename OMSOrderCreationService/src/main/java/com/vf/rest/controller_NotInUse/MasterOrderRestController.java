package com.vf.rest.controller_NotInUse;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vf.exception.MasterOrderNotFoundException;
import com.vf.model.MasterOrder;
import com.vf.repository.MasterOrderRepository;

@RestController
public class MasterOrderRestController {

@Autowired
    MasterOrderRepository OrderRepository;

// Get All Orders
	@CrossOrigin
	@GetMapping("/masterorders")
    public List<MasterOrder> getAllOrders() {
        return OrderRepository.findAll();
    }

	@GetMapping("/hello1")
    public String sayhello() {
        return "Welcome1";
    }
	
	
// Create a new Order
    @PostMapping("/masterorders")
    public MasterOrder createOrderOrder(@Valid @RequestBody MasterOrder order) {
        return OrderRepository.save(order);
    }

// Get a Single Order
    @GetMapping("/masterorders/{id}")
    public MasterOrder getOrderById(@PathVariable(value = "id") Long id) throws MasterOrderNotFoundException {
        return OrderRepository.findById(id)
                .orElseThrow(() -> new MasterOrderNotFoundException(id));
    }

// Update a Order
    @PutMapping("/masterorders/{id}")
    public MasterOrder updateOrder(@PathVariable(value = "id") Long id,
                           @Valid @RequestBody MasterOrder orderDetails) throws MasterOrderNotFoundException {

MasterOrder order = OrderRepository.findById(id)
                .orElseThrow(() -> new MasterOrderNotFoundException(id));

order.setCustomerAccountId(orderDetails.getCustomerAccountId());
order.setCustomerName(orderDetails.getCustomerName());
order.setOrderRefNumber(orderDetails.getOrderRefNumber());
order.setOrderStatus(orderDetails.getOrderStatus());
order.setOrderStatus(orderDetails.getOrderStatus());
MasterOrder updatedOrder = OrderRepository.save(order);

return updatedOrder;
    }

// Delete a Order
    @DeleteMapping("/masterorders/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable(value = "id") Long id) throws MasterOrderNotFoundException {
        MasterOrder order = OrderRepository.findById(id)
                .orElseThrow(() -> new MasterOrderNotFoundException(id));

OrderRepository.delete(order);

return ResponseEntity.ok().build();
    }
}