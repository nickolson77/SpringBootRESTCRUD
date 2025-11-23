package com.example.demo.controller;

import com.example.demo.repository.entity.Order;
import com.example.demo.service.OrderService;
import com.example.demo.service.dto.OrderResponseDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<OrderResponseDTO> getAllOrders(){
        return orderService.findAll();
    }

    @PostMapping
    public List<OrderResponseDTO> createOrders(@RequestBody List<Order> orders){
        return orderService.createOrders(orders);
    }
}
