package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.StoreRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.entity.Order;
import com.example.demo.repository.entity.Store;
import com.example.demo.service.dto.OrderResponseDTO;
import com.example.demo.service.mapper.OrderMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrderService {

    private final StoreRepository storeRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    public OrderService(OrderRepository orderRepository,
                        StoreRepository storeRepository,
                        UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.storeRepository = storeRepository;
    }

    public List<OrderResponseDTO> findAll(){
        return INSTANCE.mapOrderToOrderResponseDTO(orderRepository.findAll());
    }

    @Transactional
    public List<OrderResponseDTO> createOrders(List<Order> orders){
        for(Order order : orders){
            //1. User exist
//            User user = userRepository.findById(order.getUser().getId())
//                    .orElseThrow(() -> new RuntimeException("User not found: " + order.getUser().getId()));
            //2. Product exist
            Store storeItem = storeRepository.findByProductId(order.getProduct_id())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("No product found for the given id: " + order.getProduct_id()));
            //3. Check ProductCount
            if(storeItem.getProductCount() <= 0){
                throw new ResourceNotFoundException("Product id: " + order.getProduct_id() + " is out of stock");
            }
            //4. Decrease ProductCount
            storeItem.setProductCount(storeItem.getProductCount() - 1);
            storeRepository.save(storeItem);
        }
        //5. Save Orders
        return INSTANCE.mapOrderToOrderResponseDTO(orderRepository.saveAll(orders));
    }
}
