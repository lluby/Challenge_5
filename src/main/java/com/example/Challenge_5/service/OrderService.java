package com.example.Challenge_5.service;

import com.example.Challenge_5.model.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    Order AddOrder(Order order);
    Order updateOrder(Order order);
    void deleteOrder(String OrderId);
    Order getOrderDetails(String OrderId);
    List<Order> getAllOrder();

}
