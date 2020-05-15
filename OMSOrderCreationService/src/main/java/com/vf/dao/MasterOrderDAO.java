package com.vf.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.vf.exception.MasterOrderNotFoundException;
import com.vf.model.MasterOrder;
import com.vf.repository.MasterOrderRepository;

@RestController
public class MasterOrderDAO {

@Autowired
    MasterOrderRepository OrderRepository;

// Get All Orders
    public List<MasterOrder> getAllOrders() {
        return OrderRepository.findAll();
    }

// Create a new Order
    public MasterOrder createOrderOrder(MasterOrder order) {
        return OrderRepository.save(order);
    }

// Get a Single Order
    public MasterOrder getOrderById(Long id) throws MasterOrderNotFoundException {
        return OrderRepository.findById(id)
                .orElseThrow(() -> new MasterOrderNotFoundException(id));
    }

// Update a Order
    public MasterOrder updateOrder(Long id,
                            MasterOrder orderDetails) throws MasterOrderNotFoundException {

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
    public boolean deleteOrder(Long id) throws MasterOrderNotFoundException {
        MasterOrder order = OrderRepository.findById(id)
                .orElseThrow(() -> new MasterOrderNotFoundException(id));

OrderRepository.delete(order);

return true;
    }
}