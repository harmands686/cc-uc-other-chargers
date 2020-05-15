package com.vf.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vf.exception.ChildOrderNotFoundException;
import com.vf.model.ChildOrder;
import com.vf.repository.ChildOrderRepository;

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

// Get a Single Order
    public ChildOrder getOrderById(Long id) throws ChildOrderNotFoundException {
        return OrderRepository.findById(id)
                .orElseThrow(() -> new ChildOrderNotFoundException(id));
    }

// Update a Order
    public ChildOrder updateOrderStatus(Long id,
                           ChildOrder orderDetails) throws ChildOrderNotFoundException {

ChildOrder order = OrderRepository.findById(id)
                .orElseThrow(() -> new ChildOrderNotFoundException(id));

order.setMobileNumber(orderDetails.getMobileNumber());
order.setAddress(orderDetails.getAddress());
order.setProduct(orderDetails.getProduct());
order.setOrderStatus(orderDetails.getOrderStatus());

ChildOrder updatedOrder = OrderRepository.save(order);

return updatedOrder;
    }

// Delete a Order
    public boolean deleteOrder(Long id) throws ChildOrderNotFoundException {
        ChildOrder order = OrderRepository.findById(id)
                .orElseThrow(() -> new ChildOrderNotFoundException(id));

OrderRepository.delete(order);

return true;
    }
}