package com.project.ecommerce.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.dto.CreateOrderRequestDTO;
import com.project.ecommerce.dto.OrderDTO;
import com.project.ecommerce.dto.OrderItemDTO;
import com.project.ecommerce.dto.SuccessResponseDTO;
import com.project.ecommerce.dto.UpdateOrderDTO;
import com.project.ecommerce.entities.EPaymentMethod;
import com.project.ecommerce.entities.Order;
import com.project.ecommerce.services.OrderService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("")
    public ResponseEntity<List<OrderDTO>> getOrdersByUser(
        
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<OrderDTO> orderDTOs = orderService.getOrdersByUser(authentication.getName());
        return ResponseEntity.ok(orderDTOs);
    }

    @PostMapping("")
    public ResponseEntity<OrderDTO> createOrder(@Valid @RequestBody CreateOrderRequestDTO createOrderRequestDTO) {
        System.out.println("Here");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(createOrderRequestDTO.getPaymentMethod() == null) {
            createOrderRequestDTO.setPaymentMethod(EPaymentMethod.CAST);
        }
        Order order = orderService.createOrder(authentication.getName(), createOrderRequestDTO.getPaymentMethod(), createOrderRequestDTO.getUserAddress());
        OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
		
		order.getOrderItems().forEach(item -> orderDTO.getOrderItems().add(modelMapper.map(item, OrderItemDTO.class)));
        return ResponseEntity.ok(orderDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderDTO>> getOrders() 
     {
        List<OrderDTO> orderDTOs = orderService.getOrders();
        return ResponseEntity.ok(orderDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrder(
        @PathVariable("id") Long orderId,
        @RequestParam(name = "email", required = true) 
        String email
    ) {
        OrderDTO orderDTO = orderService.getOrder(email, orderId);
        return ResponseEntity.ok(orderDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponseDTO> updateStatusOrder(@PathVariable Long id, @Valid @RequestBody UpdateOrderDTO updateOrderDTO) {
        return ResponseEntity.ok(new SuccessResponseDTO("success", orderService.updateOrder(updateOrderDTO.getEmail(), id, updateOrderDTO.getStatus())));
    }
}
