package com.example.demo.service.mapper;

import com.example.demo.repository.entity.Order;
import com.example.demo.service.dto.OrderResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(source = "user_id", target = "userId")
    @Mapping(source = "product_id", target = "productId")
    OrderResponseDTO mapOrderToOrderResponseDTO(Order order);
    List<OrderResponseDTO> mapOrderToOrderResponseDTO(List<Order> orders);
}
