package com.project.ecommerce.services;

import java.util.List;

import com.project.ecommerce.dto.OrderDTO;
import com.project.ecommerce.entities.EOrderStatus;
import com.project.ecommerce.entities.EPaymentMethod;
import com.project.ecommerce.entities.Order;

public interface IOrderService {
    Order createOrder(String email, EPaymentMethod paymentMethod, String userAddress);
    OrderDTO getOrder(String email, Long orderId);
    List<OrderDTO> getOrdersByUser(String email);
    String updateOrder(String email, Long orderId, EOrderStatus orderStatus);
}
