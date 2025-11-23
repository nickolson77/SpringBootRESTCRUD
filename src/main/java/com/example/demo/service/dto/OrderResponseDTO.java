package com.example.demo.service.dto;

import com.example.demo.service.type.OrderStatusType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderResponseDTO {
    private Long id;
    private String userId;
    private String productId;
    private OrderStatusType status;
    private LocalDateTime created;
}
